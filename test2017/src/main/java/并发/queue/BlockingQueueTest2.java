package 并发.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/** 
* @author: liangzhenwei
* @create：2018年1月10日 上午11:06:30 
* @company:广州荔支网络技术有限公司
* @description
* blockqueue:满了、空了则等待
*/
public class BlockingQueueTest2 {
    /**
     * 
     * 定义装苹果的篮子
     * 
     */
    public class Basket {
        // 篮子，能够容纳3个苹果
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);

        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            basket.put("An apple");
        }

        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // take方法取出一个苹果，若basket为空，等到basket有苹果为止(获取并移除此队列的头部)
            return basket.take();
        }
    }

    // 定义苹果生产者
    class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 生产苹果
                    System.out.println("生产者准备生产苹果：" + instance);
                    basket.produce();
                    System.out.println("!生产者生产苹果完毕：" + instance);
                    // 休眠300ms
                    Thread.sleep(2000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }

    // 定义苹果消费者
    class Consumer implements Runnable {
        private String instance;
        private Basket basket;

        public Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 消费苹果
                	long start=System.currentTimeMillis();
                    System.out.println("消费者准备消费苹果：" + instance);
                    System.out.println(basket.consume());
                    System.out.println("!消费者消费苹果完毕：" + instance+"耗时："+(System.currentTimeMillis()-start));
                    // 休眠1000ms
//                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
                System.out.println("Consumer Interrupted");
            }
        }
    }

    public static void main(String[] args) {
//    	new LinkedBlockingQueue<String>(3).put(e);
    	
        BlockingQueueTest2 test = new BlockingQueueTest2();

        // 建立一个装苹果的篮子
        Basket basket = test.new Basket();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = test.new Producer("生产者001", basket);
        Producer producer2 = test.new Producer("生产者002", basket);
        Consumer consumer = test.new Consumer("消费者aaa", basket);
        service.submit(producer);
//        service.submit(producer2);
        service.submit(consumer);
        // 程序运行5s后，所有任务停止
//        try {
//            Thread.sleep(1000 * 5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        service.shutdownNow();
    }

}
