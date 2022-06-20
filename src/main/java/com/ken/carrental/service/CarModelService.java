package com.ken.carrental.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.carrental.common.BaseEntity;
import com.ken.carrental.entity.CarModel;
import com.ken.carrental.repository.CarMapper;
import com.ken.carrental.repository.CarModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CarModelService extends ServiceImpl<CarModelMapper, CarModel> {

    @Autowired
    private CarModelMapper carModelMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * return available car model in a period of time
     * @param startTime
     * @param endTime
     * @return
     */
    public List<CarModel> findAvailableCarModel(Date startTime, Date endTime) {
        List<Integer> carModelIds = carMapper.findAvailableCarModel(startTime, endTime);

        if (CollectionUtils.isEmpty(carModelIds)) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<CarModel> lqw = new LambdaQueryWrapper<>();
        lqw.in(BaseEntity::getId, carModelIds);

        return carModelMapper.selectList(lqw);
    }

}
