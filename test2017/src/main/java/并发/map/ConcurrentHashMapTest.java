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
