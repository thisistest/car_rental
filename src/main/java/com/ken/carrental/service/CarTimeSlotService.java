package com.ken.carrental.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.carrental.common.CarTimeSlotStatus;
import com.ken.carrental.entity.CarTimeSlot;
import com.ken.carrental.repository.CarTimeSlotMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CarTimeSlotService extends ServiceImpl<CarTimeSlotMapper, CarTimeSlot> {


    @Transactional
    public int acquireTimeSlot(Integer orderId, Integer userId, Integer carModelId, Date startTime, Date endTime, long timeSlotCount) {
        return this.getBaseMapper().acquireTimeSlot(orderId, userId, carModelId, startTime, endTime, timeSlotCount);
    }

    public Integer getCarIdByOrderId(Integer orderId) {
        LambdaQueryWrapper<CarTimeSlot> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CarTimeSlot::getOrderId, orderId);
        lqw.last("limit 1");
        CarTimeSlot carTimeSlot = getBaseMapper().selectOne(lqw);
        return carTimeSlot.getCarId();
    }

    @Transactional
    public int releaseTimeSlot(Integer orderId) {
        LambdaUpdateWrapper<CarTimeSlot> luw = new LambdaUpdateWrapper<>();
        luw.set(CarTimeSlot::getOrderId, null);
        luw.set(CarTimeSlot::getStatus, CarTimeSlotStatus.UNUSED.getCode());
        luw.eq(CarTimeSlot::getOrderId, orderId);
        return getBaseMapper().update(null, luw);
    }

}
