package com.ken.carrental.common;

import lombok.Getter;

@Getter
public enum CarTimeSlotStatus implements baseEnum {

    USED(1, "used"),
    UNUSED(0, "unused");

    CarTimeSlotStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

}
