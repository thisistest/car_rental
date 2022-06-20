package com.ken.carrental.controller;

import com.ken.carrental.common.BaseResponse;
import com.ken.carrental.common.ResponseUtil;
import com.ken.carrental.dto.CreateOrderReq;
import com.ken.carrental.entity.Order;
import com.ken.carrental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public BaseResponse<List<Order>> list() {
        return ResponseUtil.success(orderService.list());
    }

    @PostMapping("")
    public BaseResponse<Order> create(@RequestBody CreateOrderReq req) {
        return ResponseUtil.success(orderService.create(req.getCarModelId(), req.getStartTime(), req.getEndTime()));
    }

    @PostMapping("/cancel/{orderId}")
    public BaseResponse<Boolean> cancel(@PathVariable("orderId") Integer orderId) {
        return ResponseUtil.success(orderService.cancel(orderId));
    }
}
