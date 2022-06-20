package com.ken.carrental.common;

/**
 * business runtime exception
 */
public class BusinessException extends RuntimeException{

    public BusinessException() {

    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BusinessException from(baseEnum baseEnum) {
        BusinessException businessException = new BusinessException();
        businessException.setCode(baseEnum.getCode());
        businessException.setMessage(baseEnum.getMessage());
        return businessException;
    }
}
