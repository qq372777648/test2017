package 并发.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: liangzhenwei
 * @create：2018年1月10日 下午2:41:46
 * @company:广州荔支网络技术有限公司
 * @description
 * ConcurrentHashMap的为什么高效？
Hashtable低效主要是因为所有访问Hashtable的线程都争夺一把锁。
如果容器有很多把锁，每一把锁控制容器中的一部分数据，那么当多个线程访问容器里的不同部分的数据时，线程之前就不会存在锁的竞争，这样就可以有效的提高并发的访问效率。
这也正是ConcurrentHashMap使用的分段锁技术。
将ConcurrentHashMap容器的数据分段存储，每一段数据分配一个Segment（锁），当线程占用其中一个Segment时，其他线程可正常访问其他段数据。
 */
public class ConcurrentHashMapTest {
	public static void main(String[] args) {
		// new ConcurrentHashMap<Long , String>().put(key, value)

		final Map conMap = new ConcurrentHashMap<Long, String>();

		for (long i = 0; i < 1; i++) {
			conMap.put(i, i + "");
		}

		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					conMap.put(100l, "100");
					Thread.sleep(100);
					conMap.put(200l, "200");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
					for (Iterator<Entry<Long, String>> iterator = conMap.entrySet().iterator(); iterator.hasNext();) {
						Entry<Long, String> entry = iterator.next();
						System.out.println(entry.getKey() + " - " + entry.getValue());
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread2.start();

	}

}
