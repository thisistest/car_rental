package com.ken.carrental.controller;

import com.ken.carrental.common.BaseResponse;
import com.ken.carrental.common.BusinessEnum;
import com.ken.carrental.common.BusinessException;
import com.ken.carrental.common.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("hello2")
    public BaseResponse<String> hello2() {
        return ResponseUtil.success("hello");
    }

    @GetMapping("testBusinessError")
    public BaseResponse<String> testBusinessError() {
        throw BusinessException.from(BusinessEnum.TEST_ERROR);
    }

    @GetMapping("testException")
    public BaseResponse<String> testException() {
        throw new RuntimeException();
    }
}
