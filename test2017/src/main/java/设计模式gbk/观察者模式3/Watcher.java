package �۲���ģʽ3;

public class Watcher implements WatcherObserver {
	private  String job;
	@Override
	public void update(WaterQualitySubject subject) {
		//���������ģʽ��ȡ
		System.out.println(job+"��ȡ��֪ͨ����ǰ��Ⱦ����Ϊ��" + subject.getPolluteLevel());
		
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
