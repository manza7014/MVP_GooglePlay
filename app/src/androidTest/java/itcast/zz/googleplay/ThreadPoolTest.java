package itcast.zz.googleplay;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangx on 2016/7/29.
 */
public class ThreadPoolTest {
    public AtomicInteger count = new AtomicInteger(0);
    public int maxCount = 3;//最大线程数量
    // 增删
    List<Runnable> list = new LinkedList<>();//链表

    public void execute(Runnable runnable) {
        list.add(runnable);

//        count++;

        if (count.incrementAndGet() <= maxCount) { //3个线程
            createNewThread();// 创建新的线程
        }
    }

    /**
     * 创建新的线程 执行线程任务
     */
    private void createNewThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 子线程
                while (true) {
                    if (list.size() >= 1) {
                        Runnable runnable = list.remove(0);// 将第0个元素取出  并且返回
                        if (runnable != null) {
                            runnable.run();// 执行任务
                        }
                    }else{
                        //没有任务 休眠
                    }
                }
            }
        }.start();
    }

}
