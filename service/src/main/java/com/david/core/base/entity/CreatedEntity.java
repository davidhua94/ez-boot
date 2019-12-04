package com.david.core.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author David hua
 * @date 2019-08-03 14:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class CreatedEntity extends IdEntity {

    @CreatedBy
    @Column(name = "create_name")
    private String createName;

}
