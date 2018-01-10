package 并发.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author: liangzhenwei
* @create：2018年1月10日 上午11:23:50 
* @company:广州荔支网络技术有限公司
* @description
* 1可重入   lock.lock();lock.lock(); //可lock多次   ，然后锁需要被释放两次才能获得真正释放
* 2可中断
* 3可限时 lock.tryLock(5, TimeUnit.SECONDS)
* 4可以设置公平锁，先来先得到   new ReentrantLock(true)
*/
public class ReentrantLockTest extends Thread {

    public static ReentrantLock lock = new ReentrantLock(true);
    public static int i = 0;

    public ReentrantLockTest(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 8; j++) {
            lock.lock();
            
            try {
//            	System.out.println(this.getName() +lock.tryLock(1, TimeUnit.SECONDS));
                System.out.println(this.getName() + " " + i);
                sleep(1);
                i++;
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                lock.unlock();
//                if (lock.isHeldByCurrentThread()) {
//                    System.out.println("lock.isHeldByCurrentThread: " + this.getName());
//                    lock.unlock();
//                }

            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");

        test1.start();
        test2.start();
        
        test1.join();
        test2.join();
        System.out.println(i);
    }
}
