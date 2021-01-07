package com.ezboot.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Map;

/**
 * @author David Hua
 * @date 2020/1/7
 * @desc
 */
public abstract class GeneratorService implements Generate {

    private static final String TEMPLATE_PATH_PREFIX = "classpath:template/";

    private Config config;

    public GeneratorService(Config config) {
        this.config = config;
    }

    @Override
    public void doGenerate(Metadata metadata) throws IOException, TemplateException {
        // 1.将Metadata转化为template所需要的数据
        Map<String, Object> templateData = buildTemplateData(metadata);

        // 2.使用freemarker渲染并输出文件
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile(TEMPLATE_PATH_PREFIX));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = cfg.getTemplate(getTemplateName());

        OutputStream fos= null;
        try{
            // TODO 从配置里面读取输出路径
            File file = new File("C://out");
            if (!file.exists()) {
                file.mkdir();
            }

            fos = new FileOutputStream(new File(file, getFileName(metadata)));
            Writer outputStreamWriter = new OutputStreamWriter(fos);
            template.process(templateData, outputStreamWriter);
        } finally {
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
    }

    String getModulePackage(String tableName) {
        // 先去掉表名前缀
        String result = getTableNameWithoutPrefix(tableName);

        // 在把表名中的"_"替换成包名里面的 "."
        return result.replace("_",".");
    }

    String getTableNameWithoutPrefix(String tableName) {
        if (tableName.startsWith(config.getDbTablePrefix())) {
            return tableName.substring(tableName.indexOf(config.getDbTablePrefix()) + config.getDbTablePrefix().length());
        }

        return tableName;
    }

    /**
     * 根据Metadata生成每个模板需要的数据,每个模板需要的数据不一样，交给他们自己实现
     * @param metadata
     * @return
     */
    public abstract Map<String, Object> buildTemplateData(Metadata metadata);

    /**
     * 获得每个模板的路径
     * @return
     */
    public abstract String getTemplateName();

    public abstract String getFileName(Metadata metadata);
}
