package com.ken.carrental.common;

import lombok.*;

/**
 * common data for response body
 * @param <T> data type
 */
@Data
@ToString(callSuper = true)
public class BaseResponse<T> {
    public static final Integer SUCCESS = 0;

    public static final Integer ERROR = 1;

    public static final String COMMON_SUCCESS_MESSAGE = "SUCCESS";

    public static final String COMMON_ERROR_MESSAGE = "ERROR";

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Integer code;

    private String message;

    private T data;
}
