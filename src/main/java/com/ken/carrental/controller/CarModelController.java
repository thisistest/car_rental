package com.ken.carrental.controller;

import com.ken.carrental.common.BaseResponse;
import com.ken.carrental.common.ResponseUtil;
import com.ken.carrental.entity.CarModel;
import com.ken.carrental.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/carModels")
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @GetMapping("available")
    public BaseResponse<List<CarModel>> findAvailableCarModel(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date start,
                                                              @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end) {
        return ResponseUtil.success(carModelService.findAvailableCarModel(start, end));
    }
}
