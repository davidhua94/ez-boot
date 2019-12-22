package com.ezboot;

import com.ezboot.core.CodeGenerator;
import com.ezboot.core.Config;

/**
 * @author David hua
 * @date 2019-12-19 23:06:47
 */
public class Application {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        CodeGenerator codeGenerator = new CodeGenerator(config);

        final String tableName = "t_student";
        codeGenerator.generate(tableName);
    }
}
