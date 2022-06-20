package com.ken.carrental.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ken.carrental.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("tb_car_model")
public class CarModel extends BaseEntity {

    private String name;

    private String imageUrl;

}
