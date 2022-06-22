package com.ken.carrental.common;

import lombok.Getter;

/**
 * define business error code and message
 */
@Getter
public enum BusinessEnum implements BaseEnum {

    TEST_ERROR(1001, "test"),
    NO_CAR_AVAILABLE(1001, "no car available"),
    INVALID_TIME(1002, "invalid time"),
    ;

    BusinessEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    Integer code;
    String message;
}
