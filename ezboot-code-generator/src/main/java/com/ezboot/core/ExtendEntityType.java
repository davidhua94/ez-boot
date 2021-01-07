package com.ezboot.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author David Hua
 * @date 2020/1/7
 * @desc
 */
@Getter
@AllArgsConstructor
public enum ExtendEntityType {
    //只有ID和创建时间的，一般日志表
    ID_ENTITY("IdEntity"),
    CREATE_ENTITY("CreatedEntity"),
    UPDATE_ENTITY("UpdatedEntity");

    private String parentClassName;
}
