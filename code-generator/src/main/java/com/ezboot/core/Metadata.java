package com.ezboot.core;

import lombok.Data;

import java.util.List;

/**
 * @author David hua
 * @date 2019-11-23 22:01:19
 * 对应数据库表元数据
 */
@Data
public class Metadata {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 列数据
     */
    public List<ColumnMetadata> columnList;

    @Data
    public static class ColumnMetadata {
        /**
         * 该列是否为主键
         */
        private boolean primaryKey;

        /**
         * 列名
         */
        private String columnName;

        /**
         * 列的数据库类型
         */
        private String columnType;

        /**
         * 列注释
         */
        private String columnComment;
    }
}
