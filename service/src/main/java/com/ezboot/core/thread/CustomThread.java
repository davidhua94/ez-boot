package com.ezboot.core.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @author David Hua
 * @date 2020/1/8
 * @desc new CustomThread 代替 new Thread创建线程
 *       主要是解决MDC在子线程中不生效的问题
 */
@Slf4j
public class CustomThread extends Thread {

    private transient final Map<String, String> contextMap = MDC.getCopyOfContextMap();

    public CustomThread() {
        super();
    }

    public CustomThread (Runnable runnable) {
        super(runnable);
    }

    @Override
    public void run() {
        if (contextMap != null) {
            MDC.setContextMap(contextMap);
        }

        try {
            super.run();
        } finally {
            MDC.clear();
        }
    }
}
