package com.ezboot.core;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author David Hua
 * @date 2020/1/7
 * @desc
 */
public interface Generate {
    /**
     * 生成代码
     * @param metadata
     * @throws IOException
     * @throws TemplateException
     */
    void doGenerate(Metadata metadata) throws IOException, TemplateException;
}
