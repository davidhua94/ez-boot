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
    private List<ColumnMetadata> columnList;

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

    public ExtendEntityType getExtendEntity() {
        if (columnList == null || columnList.isEmpty()) {
            throw new RuntimeException("");
        }

        // 最基本的表 继承IdEntity
        ExtendEntityType result = ExtendEntityType.ID_ENTITY;
        for (ColumnMetadata columnMetadata:columnList) {
            // 如果这个表有create_name这一列，则继承CreatedEntity
            if ("create_name".equals(columnMetadata.getColumnName())) {
                result = ExtendEntityType.CREATE_ENTITY;
            }

            // 如果这个表有update_name这一列，则继承CreatedEntity
            if ("update_name".equals(columnMetadata.getColumnName())) {
                result = ExtendEntityType.UPDATE_ENTITY;
            }
        }
        return result;
    }
}
