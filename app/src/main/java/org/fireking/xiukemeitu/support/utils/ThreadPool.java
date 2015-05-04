package org.fireking.xiukemeitu.support.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Created by wanggang on 15/4/30.
 */
public class ThreadPool {

    private ExecutorService excutor;

    private ThreadPool() {
        if (excutor == null)
            excutor = Executors.newFixedThreadPool(5);
    }

    private static ThreadPool instance = null;

    public static ThreadPool newInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    /**
     * 执行线程操作
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        excutor.execute(runnable);
    }

    /**
     * 执行任务
     * @param callable
     */
    public void submit(Callable callable){
        excutor.submit(callable);
    }
}
