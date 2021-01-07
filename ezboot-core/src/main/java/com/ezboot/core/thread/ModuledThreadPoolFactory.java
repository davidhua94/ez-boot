package com.ezboot.core.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author David Hua
 * @date 2020/9/21
 * @desc TODO
 */
@Slf4j
public class ModuledThreadPoolFactory {
    private ModuledThreadPoolFactory(){}

    private static Map<String, ThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<>();

    /**
     * key: module name, means what is this thread pool used for
     * value: last used time
     */
    private static Map<String, Long> lastTimeMap = new ConcurrentHashMap<>();

    /**
     * 30 minutes
     */
    private static final int DEFAULT_THREAD_POOL_DESTROY_TIME = 1000 * 60 * 30;

    private static volatile boolean init = false;

    public static void init() {
        if(!init) {
            // check and destroy thread pools that have not been used for a while
            new Thread(() -> {
                try {
                    Thread.sleep(1000L * 60 * 5);
                } catch (InterruptedException e) {
                    log.warn("Interrupt");
                    Thread.currentThread().interrupt();
                }

                long now = System.currentTimeMillis();
                lastTimeMap.forEach((key, value) -> {
                    if (now - value > DEFAULT_THREAD_POOL_DESTROY_TIME) {
                        ThreadPoolExecutor threadPoolExecutor = threadPoolMap.get(key);
                        if (threadPoolExecutor.getActiveCount() == 0
                                && threadPoolExecutor.getQueue().isEmpty()) {
                            threadPoolExecutor.shutdownNow();
                            threadPoolExecutor = null;
                            threadPoolMap.remove(key);
                            lastTimeMap.remove(key);
                        }
                    }
                });
            }, "ThreadPoolMonitor").start();

            init = true;
        }
    }

    private static ThreadPoolExecutor getThreadPool(String module, int coreSize) {
        if (threadPoolMap.containsKey(module)) {
            lastTimeMap.put(module, System.currentTimeMillis());
            return threadPoolMap.get(module);
        }

        return createThreadPool(module, coreSize);
    }

    /**
     * get a small thread pool
     * @param module what is this thread pool used for
     * @return a 5 core sized thread pool
     */
    public static ThreadPoolExecutor getSmallThreadPool(String module) {
        return getThreadPool(module, 5);
    }

    /**
     * Get a medium-sized thread pool
     * @param module what is this thread pool used for
     * @return a 10 core sized thread pool
     */
    public static ThreadPoolExecutor getMediumThreadPool(String module) {
       return getThreadPool(module, 10);
    }

    public static ThreadPoolExecutor getSingleThreadPool(String module) {
        return getThreadPool(module, 1);
    }

    /**
     * @param module which module use this thread pool
     * @return a available thread pool
     */
    private static ThreadPoolExecutor createThreadPool(String module, int coreSize) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(coreSize, coreSize * 2,
                5L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                new CustomThreadFactory(module), new ThreadPoolExecutor.AbortPolicy());

        threadPoolMap.put(module, threadPool);
        lastTimeMap.put(module, System.currentTimeMillis());
        return threadPool;
    }
}
