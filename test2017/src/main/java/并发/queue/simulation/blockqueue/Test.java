package 并发.queue.simulation.blockqueue;
/** 
* @author: liangzhenwei
* @create：2018年1月10日 下午12:48:25 
* @company:广州荔支网络技术有限公司
* @description
*/
public class Test {
	public static void main(String[] args) throws InterruptedException {
		final MyBlockQueue q=new MyBlockQueue<String>(30);
		
		new Thread(){
			public void run() {
				for (int i = 0; i < 40; i++) {
					q.put(i+"");
					System.out.println("produce:"+i+",queue size:"+q.size());
					try {
						//sleep(100);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("produce end");
				
			};
		}.start();
		
		
		new Thread(){
			public void run() {
				for (int i = 10; i < 20; i++) {
					q.put(i+"");
					System.out.println("produce2:"+i+",queue size:"+q.size());
					try {
//						sleep(100);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("produce2 end");
				
			};
		}.start();
		
		
		new Thread(){
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("consume:"+q.take());
					try {
//						sleep(200);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("consume end");
				
			};
		}.start();
		
//		Thread.sleep(5000);
		
		new Thread(){
			public void run() {
				for (int i = 30; i < 40; i++) {
					q.put(i+"");
					System.out.println("produce3:"+i+",queue size:"+q.size());
					try {
//						sleep(200);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("produce3 end");
				
			};
		}.start();
		
		int remian=20;
		while(remian>0&&q.take()!=null){
			--remian;
		}
		
		Thread.sleep(1000);
		System.out.println(q.size());
		
		
	}
	
	
	

}
