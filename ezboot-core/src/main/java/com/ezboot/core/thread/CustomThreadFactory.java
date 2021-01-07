package com.ezboot.core.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author David Hua
 * @date 2021/1/7
 * @desc
 */
public class CustomThreadFactory implements ThreadFactory {
    private String module;

    public CustomThreadFactory(String module) {
        this.module = module;
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
