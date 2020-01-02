package com.ezboot.core;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author David hua
 * @date 2019-08-18 21:35
 */
@Getter
@Setter
@ToString
public class CurrentAdmin implements Serializable, UserDetails {
    private String username;

    private String password;

    private Boolean enabled;

    private Set<String> permissions;

    private Set<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = Lists.newArrayList();
        permissions.forEach(p -> result.add(new SimpleGrantedAuthority(p)));
        return result;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
