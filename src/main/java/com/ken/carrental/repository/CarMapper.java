package com.ken.carrental.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.carrental.entity.Car;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CarMapper extends BaseMapper<Car> {

    List<Integer> findAvailableCarModel(@Param("start") Date startTime, @Param("end") Date endTime);
}
