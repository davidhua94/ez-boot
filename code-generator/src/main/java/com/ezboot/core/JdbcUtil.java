package com.ezboot.core;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David hua
 * @date 2019-11-23 22:00:52
 */
@Slf4j
public class JdbcUtil {

    public static Connection getConnection(Config config) throws ClassNotFoundException, SQLException {
        Class.forName(config.getDriverClass());

        return DriverManager.getConnection(config.getJdbcUrl(), config.getJdbcUser(), config.getPassword());
    }

    public static Metadata getMetadata(String tableName, Connection connection) throws Exception {
        Metadata metadata = new Metadata();
        metadata.setTableName(tableName);
        metadata.setTableComment(""); //?

        List<Metadata.ColumnMetadata> columnMetadataList = new ArrayList<>();

        DatabaseMetaData metaData = connection.getMetaData();
        // 获取所有的表
        ResultSet tables = metaData.getTables(null, "%", tableName, new String[]{"TABLE"});
        while (tables.next()) {
            String tempTableName = tables.getString("TABLE_NAME");
            if (tempTableName.equals(tableName)) {
                // 获得指定表的数据
                ResultSet rs = connection.getMetaData().getColumns(null, null,tableName, "%");
                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME");
                    String remarks = rs.getString("REMARKS");
                    String dbType = rs.getString("TYPE_NAME");
                    String columnSize = rs.getString("COLUMN_SIZE");
                    log.info("name={},type={},size={},remark={}", columnName, dbType, columnSize, remarks);
                    Metadata.ColumnMetadata columnMetadata = new Metadata.ColumnMetadata();
                    columnMetadata.setColumnName(columnName);
                    columnMetadata.setColumnComment(remarks);
                    columnMetadata.setColumnType(dbType.split(" ")[0]);
                    columnMetadata.setPrimaryKey(false); //?
                    columnMetadataList.add(columnMetadata);
                }
            }
        }
        metadata.setColumnList(columnMetadataList);
        return metadata;
    }

    private static String getSchema(Connection connection) throws Exception {
        String schema;
        schema = connection.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase();
    }
}
