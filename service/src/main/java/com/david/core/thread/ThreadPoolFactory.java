package com.david.core.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author David hua
 * @date 2019-08-13 22:55
 */
@Slf4j
public class ThreadPoolFactory {
    /**
     * 存放整个项目线程池
     * key : 功能名
     * value : 所使用的线程池
     */
    private static Map<String, ThreadPoolExecutor> threadPoolExecutorMap;

    /**
     * key : 功能名
     * value : 该线程池创建时间
     */
    private static Map<String, Long> createTimeMap;

    private static boolean init = false;

    /**
     * 默认闲置30分钟过期
     */
    private static final long DEFAULT_EXPIRE_TIME = 1000 * 60 * 30;

    /**
     * 轮询检测
     */
    private static final ExecutorService thread = Executors.newSingleThreadExecutor();


    private static void init() {
        if (init) {
            return;
        }

        threadPoolExecutorMap = new ConcurrentHashMap<>();
        createTimeMap = new ConcurrentHashMap<>();

        /**
         * 创建一个线程用于监控
         * 每隔5分钟, 检查闲置线程池并回收
         */
        thread.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(1000 * 60 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<String> needRemovedKey = new ArrayList<>();
                for (String key : threadPoolExecutorMap.keySet()) {
                    ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(key);
                    Long startTime = createTimeMap.get(key);

                    if (threadPoolExecutor.getActiveCount() == 0
                            && System.currentTimeMillis() - startTime > DEFAULT_EXPIRE_TIME) {
                        needRemovedKey.add(key);
                    }
                }

                for (String key : needRemovedKey) {
                    ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(key);
                    threadPoolExecutor.shutdown();
                    threadPoolExecutor = null;

                    threadPoolExecutorMap.remove(key);
                    createTimeMap.remove(key);
                }
            }
        });

        init = true;
    }

    /**
     * 根据功能获取线程池
     */
    public static ThreadPoolExecutor getByModuleName(String moduleName, int poolSize) {
        init();

        if (threadPoolExecutorMap.containsKey(moduleName)) {
            return threadPoolExecutorMap.get(moduleName);
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSize, poolSize * 4,
                1000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutorMap.put(moduleName, threadPoolExecutor);
        createTimeMap.put(moduleName, System.currentTimeMillis());

        return threadPoolExecutor;
    }
}
