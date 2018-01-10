package 并发.queue.simulation.blockqueue;

import java.util.LinkedList;
import java.util.List;

/** 
* @author: liangzhenwei
* @create：2018年1月10日 下午12:37:03 
* @company:广州荔支网络技术有限公司
* @description
*/
public class MyBlockQueue<E> {
	private List<E> queue=new LinkedList<E>();
	private int maxSize;
	private int cIndex;
	
	private String putLock="MyBlockQueue.putLock!$#@$@$~!@#36";
	private String takeLock="MyBlockQueue.takeLock!$#@$@$~!@#36";
	
	private String putWaitLock="MyBlockQueue.putWaitLock!$#@$@$~!@#36";
	private String takeWaitLock="MyBlockQueue.takeWaitLock!$#@$@$~!@#36";
	
	
	
	public MyBlockQueue(int maxSize) {
		super();
		if(maxSize<1)
			maxSize=1;
		this.maxSize = maxSize;
	}
	
	public  void put(E e){
		synchronized(putLock){
			while(queue.size()>=maxSize){
				try {
					synchronized(putWaitLock){
						putWaitLock.wait();
					}
				} catch (InterruptedException e1) {
				}
			}
			queue.add(e);
			//queue size must gt 0
			synchronized(takeWaitLock){
				takeWaitLock.notify();//notify one take thread 
			}
			
		}
	}
	public E take(){
		synchronized(takeLock){
			if(queue.size()>=0){
				//没有则等待
				while(queue.size()<1)
					synchronized(takeWaitLock){
						try {
							takeWaitLock.wait();
						} catch (InterruptedException e) {
						}
					}
				//must gt 0 ,can take
				E e=queue.remove(0);
				//after take,notify the waiting put thread
				synchronized(putWaitLock){
					putWaitLock.notify();//notify one put thread 
				}
				return e;
			}else {
				throw new RuntimeException("out of controller");
			}
		}
	}
	
	public int size(){
		return queue.size();
	}

}
