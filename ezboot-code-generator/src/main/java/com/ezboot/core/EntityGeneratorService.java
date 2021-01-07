package com.ezboot.core;

import java.util.*;

/**
 * @author David Hua
 * @date 2020/1/7
 * @desc
 */
public class EntityGeneratorService extends GeneratorService {

    private static final List<String> IGNORE_COLUMNS = Arrays.asList("id","create_name","create_time","update_name","update_time");

    private Config config;

    public EntityGeneratorService(Config config) {
        super(config);
        this.config = config;
    }

    @Override
    public Map<String, Object> buildTemplateData(Metadata metadata) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("basePackage", config.getBasePackage());
        dataMap.put("modulePackage", getModulePackage(metadata.getTableName()));
        dataMap.put("tableName", metadata.getTableName());
        dataMap.put("entityName", getEntityClassName(metadata.getTableName()));
        dataMap.put("parentEntityClass", metadata.getExtendEntity().getParentClassName());

        List<Map<String, String>> entityFieldList = new ArrayList<>();
        //
        metadata.getColumnList().forEach(column -> {
            if (!IGNORE_COLUMNS.contains(column.getColumnName())) {
                Map<String,String> map = new HashMap<>();
                map.put("columnName", column.getColumnName());
                map.put("fieldType", getFieldType(column.getColumnType()));
                map.put("fieldName", getFieldName(column.getColumnName()));
                entityFieldList.add(map);
            }
        });

        dataMap.put("entityFieldList", entityFieldList);
        return dataMap;
    }

    private String getFieldName(String columnName) {
        //列名转属性名
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : columnName.split("_")) {
            String s = word.toLowerCase();
            stringBuilder.append(s.substring(0,1).toUpperCase()).append(s.substring(1));
        }

        return stringBuilder.substring(0,1).toLowerCase() + stringBuilder.substring(1);
    }

    /**
     * 数据库字段类型转化成实体类属性类型
     */
    private String getFieldType(String columnType) {
        switch (columnType) {
            case "VARCHAR":
                return "String";
            case "INT":
                return "Integer";
                // TODO 直接返回Date, 用import导包
            case "DATETIME":
                return "java.util.Date";
            case "TIMESTAMP":
                return "java.util.Date";
            case "BIT":
                return "Boolean";

            default:
                throw new RuntimeException("不支持的数据库字段类型 :" + columnType);
        }

    }

    @Override
    public String getTemplateName() {
        return "entity.ftl";
    }

    @Override
    public String getFileName(Metadata metadata) {
        //拿到类名，加上.java
        return String.format("%s.java", getEntityClassName(metadata.getTableName()));
    }

    private String getEntityClassName(String tableName) {
        String tableNameWithoutPrefix = getTableNameWithoutPrefix(tableName);

        // 去掉下划线改成大驼峰
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : tableNameWithoutPrefix.split("_")) {
            String s = word.toLowerCase();
            stringBuilder.append(s.substring(0,1).toUpperCase()).append(s.substring(1));
        }

        return stringBuilder.toString();
    }
}
