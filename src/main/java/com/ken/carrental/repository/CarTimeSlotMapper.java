package com.ken.carrental.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.carrental.entity.CarTimeSlot;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CarTimeSlotMapper extends BaseMapper<CarTimeSlot> {

    /**
     * acquire time slots by car model
     * @param orderId
     * @param userId
     * @param carModelId
     * @param startTime
     * @param endTime
     * @param timeSlotCount
     * @return time slots count been acquired
     */
    int acquireTimeSlot(@Param("orderId")Integer orderId,
                       @Param("userId") Integer userId,
                       @Param("carModelId") Integer carModelId,
                       @Param("startTime") Date startTime,
                       @Param("endTime") Date endTime,
                       @Param("timeSlotCount") long timeSlotCount);
}
