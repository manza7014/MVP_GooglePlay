package itcast.zz.googleplay.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangx on 2016/7/29.
 * 线程池管理器
 */
public class ThreadPoolManager {

    private ThreadPoolProxy poolProxy;
    private ThreadPoolProxy shortThreadPoolProxy;

    // 单例: 懒汉式   饿汉式(线程安全)
    //1 私有化构造方法
    private ThreadPoolManager() {
    }

    private static final ThreadPoolManager intance = new ThreadPoolManager();

    public static ThreadPoolManager getIntance() {
        return intance;
    }

    // 如何效率最高  cpu*2+1
    // 1.联网
    //2. 读取文件


    public ThreadPoolProxy createThreadPool() {
        if (poolProxy == null)
            poolProxy = new ThreadPoolProxy(3, 3, 5000);
        return poolProxy;
    }

    public ThreadPoolProxy createShortThreadPool() {
        if (shortThreadPoolProxy == null)
            shortThreadPoolProxy = new ThreadPoolProxy(3, 3, 5000);
        return shortThreadPoolProxy;
    }

    /***
     * 线程池的配置对象
     */
    public class ThreadPoolProxy {

        ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 执行线程任务
         *
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (threadPool == null) {
                //创建线程池
                //1.  corePoolSize 初始化线程池的线程数量
                //2. maximumPoolSize除了初始化线程数量还能额外开辟的线程数量
                //3. keepAliveTime存活的时长

//                5.FIFO 先进先出
                threadPool = new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(10));
            }
            threadPool.execute(runnable);
        }

        /**
         * 取消线程任务
         */
        public void cancel(Runnable runnable) {
            if (threadPool != null && !threadPool.isShutdown() && !threadPool.isTerminated())
                threadPool.remove(runnable);
        }
    }
}
