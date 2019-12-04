package com.david.core.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author David hua
 * @date 2019-08-13 22:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class UpdatedEntity extends CreatedEntity {

    @LastModifiedBy
    @Column(name = "update_name")
    private String updateName;

    @Column(name = "update_time")
    @LastModifiedDate
    private Date updateTime;
}
