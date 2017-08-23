package core.集合.theadlocal;

import org.apache.log4j.TTCCLayout;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月15日 下午3:51:31 
* @version:1.0
* @description:
*/
public class TestTheadLocal {
	static ThreadLocal<String> threadLocal=new ThreadLocal<String>();
	
	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				super.run();
				threadLocal.set("1");
				for (int i = 0; i < 10; i++) {
					
					System.out.println("我是一"+threadLocal.get());
					try {
						sleep(5000);
					} catch (InterruptedException e) {
					}
				}
				
			}
		}.start();
		
		threadLocal.set("2");
		
		new Thread(){
			@Override
			public void run() {
				super.run();
				threadLocal.set("3");
				for (int i = 0; i < 10; i++) {
					
					System.out.println("我是三"+threadLocal.get());
					try {
						sleep(5000);
					} catch (InterruptedException e) {
					}
				}
				
			}
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				super.run();
				for (int i = 0; i < 10; i++) {
					
					System.out.println("我是空"+threadLocal.get());
					try {
						sleep(5000);
					} catch (InterruptedException e) {
					}
				}
				
			}
		}.start();
		
		System.out.println("我是二"+threadLocal.get());
		
	}
	
	

}
