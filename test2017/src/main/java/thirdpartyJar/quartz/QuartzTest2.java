package thirdpartyJar.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;  
  
public class QuartzTest2 {  
    public static void main(String[] args) {  
        runQuartz();  
   }  
   public static void runQuartz(){  
    try {  
     //定义任务 分组名称信息 分组名 如果不设置分组名 是默认分组Default    
     JobDetail jobDetail =  new JobDetailImpl("myJobName","myJobGroupName", MyJob.class);  
     //定义调度触发器  
     SimpleTriggerImpl strigger = new SimpleTriggerImpl("myTiggerName");  
     //触发器 从当前时间开始  
//     strigger.setStartTime(new Date());  
     //调用10次  
     strigger.setRepeatCount(10);  
     //每隔2000毫秒  
     strigger.setRepeatInterval(5);  
     //定义调度  
     Scheduler scheudle = new StdSchedulerFactory().getScheduler();  
     scheudle.scheduleJob(jobDetail, strigger);  
     //调度启动  
     scheudle.start();  
     
     
     Thread.currentThread().sleep(1000000);  
     //调度关闭  
     System.err.println("关闭  ");  
     scheudle.shutdown();  
 } catch (Exception e) {  
     e.printStackTrace();  
 }  
}  
}  


