package test2017.jvm.classloader;
/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月22日 下午2:07:42 
* @version:1.0
* @description:
*/
public class Test1 {
	public static void main(String[] args) {
	        
	        System.out.println(Thread.currentThread().getContextClassLoader());
	        System.out.println(Test1.class.getClassLoader());
	        new Thread(){
	        	public void run() {
	        		System.out.println("1"+Thread.currentThread().getContextClassLoader());
	        	};
	        }.start();
	        new Thread(){
	        	public void run() {
	        		System.out.println("2"+Thread.currentThread().getContextClassLoader());
	        	};
	        }.start();
	        
	        
	        System.out.println(Test1.class.getClassLoader().getResource("aa"));
	        System.out.println(Test1.class.getClassLoader().getResource("1"));
	        System.out.println(Test1.class.getClassLoader().getResourceAsStream("1"));
	}
	

}
