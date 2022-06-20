package com.ken.carrental.common;

public final class ResponseUtil {

    public static BaseResponse<?> success() {
        return new BaseResponse<Void>(BaseResponse.SUCCESS, BaseResponse.COMMON_SUCCESS_MESSAGE, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(BaseResponse.SUCCESS, BaseResponse.COMMON_SUCCESS_MESSAGE, data);
    }
}
