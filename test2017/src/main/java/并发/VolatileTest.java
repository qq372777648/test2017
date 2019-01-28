package 并发;

import java.util.concurrent.TimeUnit;

/** 
* @author: liangzhenwei
* @create：2018年8月14日 下午5:45:54 
* @company:广州荔支网络技术有限公司
* @description
*/
public class VolatileTest {
	static volatile int bit=0;
	static volatile boolean  readWait=true;

	public static void main(String[] args) throws InterruptedException {
		final long start=System.currentTimeMillis();
		Thread t = new Thread(){
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis()-start);
//				System.out.println(A.a);
			}
		};
//        Runtime.getRuntime().addShutdownHook(t);
		
//		for (int i = 0; i < 30000; i++) {
//			new Thread(){
//				public void run() {
//					A.a++;
//				};
//			}.start();
//		}
        
//	        for (long i = 0; i < 1000000000000l; i++) {
//						if(new A().a==0){
//							System.out.println(1);
//						}
//        }
//	        
	        
		for (int i = 0; i < 1; i++) {
			new Thread(){
				public void run() {
					
					
					while (true) {
						if(A.cSoutCount<A.MaxSoutCount){
							A.cSoutCount++;
//							try {
//								TimeUnit.MICROSECONDS.sleep(1);
//							} catch (InterruptedException e) {
//							}
							while(A.cSoutCount==A.MaxSoutCount){
								continue;
							}
							System.out.println(A.a);
							continue;
						}
						break;
					}
				};
			}.start();
		}
		
		
		new Thread(){
			public void run() {
				while(true){
					if(A.cSoutCount==A.MaxSoutCount){
//						try {
//							TimeUnit.MICROSECONDS.sleep(1);
//						} catch (InterruptedException e) {
//						}
						
						A.a="22222";
						A.cSoutCount++;
						break;
					}
				}
			};
		}.start();
			
			
        
		

	}
	
}


class A{
	public   static   String a="111111111";
	volatile static  int  cSoutCount=0;
	final static int MaxSoutCount=3;
	
	volatile static  boolean  isMax=false;
}
