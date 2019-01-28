package thirdpartyJar.quartz;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/** 
* @author: liangzhenwei
* @create：2018年3月1日 下午5:51:34 
* @company:广州荔支网络技术有限公司
* @description
*/
public class Test3 {
	/** 每天23点开始 */
	private static final String cronexp = "0 0 23 * * ?";
	
	private static Scheduler scheduler;
	private static boolean isStarted = false;
	
	private ResetPrizePoolSchedulerManager() {
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			scheduler = sf.getScheduler();
		} catch (Exception e) {
			throw new RuntimeException("[ResetPrizePoolSchedulerManager] Failed to start scheduler, reason: " + e.getMessage(), e);
		}
	}


	public synchronized void start() {
		if(!isStarted){
			JobDetail jobDetail = new JobDetail("Job", null, InitPrizePoolJob.class);
			CronTrigger cronTrigger;
			try {
				cronTrigger = new CronTrigger ("CronTrigger", "Group", cronexp);
			} catch (ParseException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
	
			try {
				scheduler.scheduleJob(jobDetail, cronTrigger);
				scheduler.start();
				isStarted = true;
			} catch (SchedulerException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}
