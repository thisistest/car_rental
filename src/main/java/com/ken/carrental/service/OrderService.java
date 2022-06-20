package com.ken.carrental.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.carrental.common.BaseEntity;
import com.ken.carrental.common.BusinessEnum;
import com.ken.carrental.common.BusinessException;
import com.ken.carrental.common.OrderStatus;
import com.ken.carrental.entity.Order;
import com.ken.carrental.repository.OrderMapper;
import com.ken.carrental.util.TimeUtil;
import com.ken.carrental.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ken.carrental.common.BusinessEnum.INVALID_TIME;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CarTimeSlotService carTimeSlotService;

    /**
     * return order if booking car successfully, otherwise throws business error
     * @param carModelId
     * @param startTime
     * @param endTime
     * @return
     */
    @Transactional
    public Order create(Integer carModelId, Date startTime, Date endTime) {
        long timeSlotCount = calculateTimeSlotCount(startTime, endTime);

        Integer userId = UserUtil.getCurrentUserId();
        Date now = new Date();

        Order order = new Order();
        order.setUserId(userId);
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        order.setStatus(OrderStatus.NORMAL.getCode());
        order.setIsDeleted(0L);
        order.setCreatedBy(userId);
        order.setCreatedTime(now);
        order.setUpdatedBy(userId);
        order.setUpdatedTime(now);
        orderMapper.insert(order);

        Integer orderId = order.getId();

        int updatedCount = carTimeSlotService.acquireTimeSlot(orderId, userId, carModelId, startTime, endTime, timeSlotCount);

        if (updatedCount != timeSlotCount) {
            throw BusinessException.from(BusinessEnum.NO_CAR_AVAILABLE);
        }

        Integer carId = carTimeSlotService.getCarIdByOrderId(orderId);
        order.setCarId(carId);

        orderMapper.updateById(order);

        return order;
    }

    private long calculateTimeSlotCount(Date startTime, Date endTime) {
        boolean valid = TimeUtil.validTime(startTime, endTime);
        if (!valid) {
            throw BusinessException.from(INVALID_TIME);
        }
        long diff = endTime.getTime() - startTime.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diff) / 30;
    }

    public List<Order> list() {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getUserId, UserUtil.getCurrentUserId());
        lqw.eq(BaseEntity::getIsDeleted, 0L);
        return orderMapper.selectList(lqw);
    }

    @Transactional
    public boolean cancel(Integer orderId) {

        carTimeSlotService.releaseTimeSlot(orderId);

        LambdaUpdateWrapper<Order> luw = new LambdaUpdateWrapper<>();
        luw.set(Order::getStatus, OrderStatus.CANCELED.getCode());
        luw.set(BaseEntity::getUpdatedBy, UserUtil.getCurrentUserId());
        luw.set(BaseEntity::getUpdatedTime, new Date());
        luw.eq(BaseEntity::getId, orderId);
        luw.eq(Order::getUserId, UserUtil.getCurrentUserId());
        luw.eq(Order::getStatus, OrderStatus.NORMAL.getCode());
        luw.eq(BaseEntity::getIsDeleted, 0L);

        return orderMapper.update(null, luw) > 0;
    }
}
