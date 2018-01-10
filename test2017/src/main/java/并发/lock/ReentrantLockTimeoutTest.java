package 并发.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author: liangzhenwei
* @create：2018年1月10日 上午11:49:03 
* @company:广州荔支网络技术有限公司
* @description
*/
public class ReentrantLockTimeoutTest extends Thread {
	private static ReentrantLock lock= new ReentrantLock();

    public ReentrantLockTimeoutTest(String name) {
        super.setName(name);
    }
	
	@Override
	public void run() {
		try {
			if(lock.tryLock(1,TimeUnit.SECONDS)){
				 System.out.println(this.getName() + "getlock");
				 sleep(2000);
			}else{
				System.out.println(this.getName() + "getlock timeout");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if (lock.isHeldByCurrentThread()) {
                System.out.println("lock.isHeldByCurrentThread: " + this.getName());
                lock.unlock();
            }
		}
		
	}
	
	public static void main(String[] args) {
		new ReentrantLockTimeoutTest("t1").start();
		new ReentrantLockTimeoutTest("t2").start();
		
	}
	
	

}
