package com.ken.carrental.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * base entity, includes common fields
 */
@Data
public abstract class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // soft delete flag, 0: undeleted, otherwise delete unix timestamp
    private Long isDeleted;

    // creator user id
    private Integer createdBy;

    // created time
    private Date createdTime;

    // updater user id
    private Integer updatedBy;

    // updated time
    private Date updatedTime;
}
