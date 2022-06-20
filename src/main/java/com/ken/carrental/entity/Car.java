package com.ken.carrental.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ken.carrental.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tb_car")
public class Car extends BaseEntity {

    private Integer carModelId;

}
