package 观察者模式3;

public class Client {
	public static void main(String[] args) {
		//创建水质被观察者对象
		WaterQuality waterQuality = new WaterQuality();
		//创建几个观察者对象
		WatcherObserver watcher1 = new Watcher();
		watcher1.setJob("监测人员");
		WatcherObserver watcher2 = new Watcher();
		watcher2.setJob("预警人员");
		WatcherObserver watcher3 = new Watcher();
		watcher3.setJob("监测部门领导");
		
		//注册观察者
		waterQuality.attach(watcher1);
		waterQuality.attach(watcher2);
		waterQuality.attach(watcher3);
		//填写水质报告
		System.out.println("当前水质为正常的时候----------------------------");
		waterQuality.setPolluteLevel(0);//仪器检测水质
		System.out.println("当前水质为轻度污染的时候----------------------------");
		waterQuality.setPolluteLevel(1);
		System.out.println("当前水质为中度的时候----------------------------");
		waterQuality.setPolluteLevel(2);
		
	}
}
