package com.ken.carrental.common;

import lombok.Getter;

@Getter
public enum OrderStatus implements BaseEnum {

    NORMAL(1, "normal"),
    CANCELED(2, "canceled");

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

}
