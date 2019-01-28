package thirdpartyJar.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	
	public static void main(String[] args) throws Exception {
		  // define the job and tie it to our MyJob class
		  JobDetail job = JobBuilder.newJob(MyJob.class)
		      .withIdentity("job1", "group1")
		      .build();

		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = TriggerBuilder.newTrigger()
		      .withIdentity("trigger1", "group1")
		      .startNow()
		      .withSchedule(SimpleScheduleBuilder.simpleSchedule()
		              .withIntervalInSeconds(1)
		              .repeatForever())
		      .build();
		  // Grab the Scheduler instance from the Factory
		  Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		  // Tell quartz to schedule the job using our trigger
		  
		  // and start it off
		  scheduler.start();
		  
		  scheduler.scheduleJob(job, trigger);
		  
		  System.out.println();
		  
	}

}


