package 并发.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * 无效
* @author: liangzhenwei
* @create：2018年1月10日 上午11:49:03 
* @company:广州荔支网络技术有限公司
* @description
*/
public class ReentrantLockFair extends Thread {
	public static ReentrantLock lock= new ReentrantLock(false);

    public ReentrantLockFair(String name) {
        super.setName(name);
    }
	
	@Override
	public void run() {
		try {
//			 sleep(1000);
			System.out.println(this.getName() + "ready");
			lock.lock();
//			sleep(1);
			System.out.println(this.getName() + "getlock");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
                lock.unlock();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockFair.lock.lock();
		for (int i = 0; i < 10; i++) {
			sleep(10);
			new ReentrantLockFair("t"+i).start();
		}
		sleep(10);
		ReentrantLockFair.lock.unlock();
		System.out.println("begin");
		
	}
	
	

}
