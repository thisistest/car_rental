package com.ken.carrental.exception;

import com.ken.carrental.common.BaseResponse;
import com.ken.carrental.common.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse<?>> commonExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        BaseResponse<Void> response = new BaseResponse<>(BaseResponse.ERROR, BaseResponse.COMMON_ERROR_MESSAGE, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<BaseResponse<?>> businessExceptionHandler(BusinessException e) {
        BaseResponse<Void> response = new BaseResponse<>(e.getCode(), e.getMessage(), null);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<?>> violationExceptionHandler(ConstraintViolationException e) {
        log.warn("bad request", e);
        String msg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("；"));
        BaseResponse<Void> response = new BaseResponse<>(BaseResponse.ERROR, msg, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
