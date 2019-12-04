package com.david.test.entity;

import com.david.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author David hua
 * @date 2019-08-03 14:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity(name = "t_student")
public class Student extends UpdatedEntity {

    private String name;

    private Integer age;

    private String description;
}
