package com.ezboot.system.permission.entity;

import com.ezboot.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author wang
 */
@Data
@DynamicUpdate
@EqualsAndHashCode(callSuper = false)
@Entity(name = "t_system_permission")
public class Permission extends UpdatedEntity {

    @Column(name = "permission_type")
    private String permissionType;

    @Column(name = "permission")
    private String permission;

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "enabled")
    private Boolean enabled;

}
