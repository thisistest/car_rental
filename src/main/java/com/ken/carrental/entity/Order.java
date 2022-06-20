package com.ken.carrental.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ken.carrental.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_order")
public class Order extends BaseEntity {

    private Integer userId;

    private Integer carId;

    private Integer status;

    private Date startTime;

    private Date endTime;
}
