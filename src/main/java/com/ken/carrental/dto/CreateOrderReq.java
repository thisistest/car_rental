package com.ken.carrental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * create order request
 * todo need to validate data
 */
@Data
public class CreateOrderReq {
    Integer carModelId;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date startTime;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    Date endTime;
}
