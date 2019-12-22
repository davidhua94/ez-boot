package com.ezboot.core;

import java.sql.Connection;

/**
 * @author David hua
 * @date 2019-11-23 22:05:32
 */
public class CodeGenerator {

    private Config config;

    public CodeGenerator(Config config) {
        this.config = config;
    }

    public void generate(String tableName) throws Exception {
        Connection connection = JdbcUtil.getConnection(config);
        Metadata metadata = JdbcUtil.getMetadata(tableName,connection);
    }
}
