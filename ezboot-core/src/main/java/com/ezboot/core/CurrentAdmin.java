package com.ezboot.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * @author David hua
 * @date 2019-08-18 21:35
 */
@Getter
@Setter
@ToString
public class CurrentAdmin implements Serializable {
    private String username;

    private String avatar;

    private Set<String> permissions;

    private Set<String> roles;
}
