package 并发.lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何测
* @author: liangzhenwei
* @create：2018年1月10日 下午12:25:03 
* @company:广州荔支网络技术有限公司
* @description
 */
public class ReentrantLock中断 extends Thread {

	public static ReentrantLock lock1 = new ReentrantLock();

	int lock;

	public ReentrantLock中断(int lock, String name) {

		super(name);
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
//				lock1.lockInterruptibly();
			lock1.lock();
				try {
					
					System.out.println(this.getName() + "getlock");
					Thread.sleep(500);
//					System.exit(0);
				} catch (Exception e) {
				}
		} catch (Exception e) {
		} finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock中断 t1 = new ReentrantLock中断(1, "LockInterrupt1");
		ReentrantLock中断 t2 = new ReentrantLock中断(2, "LockInterrupt2");
		t1.start();
		t2.start();
		Thread.sleep(1000);

		// DeadlockChecker.check();
	}

	static class DeadlockChecker {

		private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

		public static void check() {

			Thread tt = new Thread() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
						if (deadlockedThreadIds != null) {
							ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
							for (Thread t : Thread.getAllStackTraces().keySet()) {
								for (int i = 0; i < threadInfos.length; i++) {
									if (t.getId() == threadInfos[i].getThreadId()) {
										System.out.println(t.getName());
										t.interrupt();
									}
								}
							}
						}
						try {
							Thread.sleep(5000);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}

				}
			};
			tt.setDaemon(true);
			tt.start();
		}

	}

}
