package com.david.system.role.entity;

import com.david.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author wang
 */
@Data
@Entity(name = "t_system_role")
@EqualsAndHashCode(callSuper = false)
public class Role extends UpdatedEntity {

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private Boolean enabled;

}
