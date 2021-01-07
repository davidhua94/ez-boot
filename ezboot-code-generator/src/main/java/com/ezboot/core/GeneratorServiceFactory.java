package com.ezboot.core;

import lombok.AllArgsConstructor;

/**
 * @author David Hua
 * @date 2020/1/7
 * @desc
 */
@AllArgsConstructor
public class GeneratorServiceFactory {

    private Config config;

    public Generate build(GeneratorType generatorType) {
        switch (generatorType) {
            case ENTITY:
                return new EntityGeneratorService(config);

            default:
                throw new RuntimeException("");
        }
    }
}
