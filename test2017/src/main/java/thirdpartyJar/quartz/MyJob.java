package thirdpartyJar.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** 
* @author: liangzhenwei
* @create：2018年3月2日 上午10:18:09 
* @company:广州荔支网络技术有限公司
* @description
*/
public class MyJob implements Job {  
	 public void execute(JobExecutionContext context) throws JobExecutionException {
	        System.err.println("Hello World!  MyJob is executing.");
	    }
} 
