package 观察者模式3;

public class Watcher implements WatcherObserver {
	private  String job;
	@Override
	public void update(WaterQualitySubject subject) {
		//这里采用拉模式获取
		System.out.println(job+"获取到通知，当前污染级别为：" + subject.getPolluteLevel());
		
	}

	@Override
	public void setJob(String job) {
		this.job = job;
		
	}

	@Override
	public String getJob() {
		
		return this.job;
	}

}
