package 并发.queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static int count = 2; // 线程个数
    //CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
    private static CountDownLatch latch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueueTest.offer();
        for (int i = 0; i < count; i++) {
            es.submit(new Poll());
        }
        latch.await(); //使得主线程(main)阻塞直到latch.countDown()为零才继续执行
        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");
        es.shutdown();
    }
    
    /**
     * 生产
     */
    public static void offer() {
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
    }


    /**
     * 消费
     *  
     * @author 林计钦
     * @version 1.0 2013-7-25 下午05:32:56
     */
    static class Poll implements Runnable {
        public void run() {
//             while (queue.size()>0) {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            
			/**	减少锁存器的计数，释放所有等待的线程，如果计数达到零。
				如果当前计数大于零，则递减。 如果新计数为零，则所有正在等待的线程都将重新启用以进行线程调度。
				如果当前计数等于零，则不会有任何反应。
			 */
            latch.countDown();
        }
    }
}
