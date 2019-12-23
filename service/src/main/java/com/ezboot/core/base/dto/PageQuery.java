package com.ezboot.core.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author David hua
 * @date 2019-11-10 11:37:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageQuery extends AbstractDTO {

    /**
     * default value: 1
     */
    private Integer page = 1;

    /**
     * default value: 10
     */
    private Integer pageSize = 10;
}
