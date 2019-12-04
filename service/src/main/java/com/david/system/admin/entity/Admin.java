package com.david.system.admin.entity;

import com.david.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author wang
 */
@Data
@Entity(name = "t_system_admin")
@EqualsAndHashCode(callSuper = false)
@DynamicUpdate
public class Admin extends UpdatedEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "enabled")
    private boolean enabled;

}
