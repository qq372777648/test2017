package 异步;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ExecutorHelper {

	private static ExecutorService executor;
	
	public static ExecutorService getTaskExe(){
		if(executor==null){
			executor = Executors.newCachedThreadPool();
		}
		return executor;
	}
	
	public static void  execute(Thread t){
		getTaskExe().execute(t);
	}
	public static void jsStyle(function function){
		
	}
	
	
	public static void main(String[] args) {
		
		new Thread(){
			
		};
		new HashMap() {
			{
				put("", "");
			}
		};
		
		jsStyle(new function() {
			public void code() {
				// TODO Auto-generated method stub
				
			}
		});
	}
}


interface function{
	void code();
}
// class CB{
//	 public static function  function(){
//		 return new function();
//	}
//}


//
//function funtion(){
//	return new function();
//}