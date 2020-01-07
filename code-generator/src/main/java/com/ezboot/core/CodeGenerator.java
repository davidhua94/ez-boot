package com.ezboot.core;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David hua
 * @date 2019-11-23 22:05:32
 */
public class CodeGenerator {

    private Config config;

    private GeneratorServiceFactory generatorServiceFactory;

    public CodeGenerator(Config config) {
        this.config = config;
        this.generatorServiceFactory = new GeneratorServiceFactory(config);
    }

    public void generate(String tableName) throws Exception {
        //1. 获取数据库连接
        Connection connection = JdbcUtil.getConnection(config);

        //2. 读到数据库表的元数据
        Metadata metadata = JdbcUtil.getMetadata(tableName,connection);

        //3. 生成代码
        List<GeneratorType> generatorTypes = new ArrayList<>();
        generatorTypes.add(GeneratorType.ENTITY);
                //config.getGeneratorTypes();
        for (GeneratorType generatorType : generatorTypes) {
            generatorServiceFactory.build(generatorType).doGenerate(metadata);
        }
    }
}
