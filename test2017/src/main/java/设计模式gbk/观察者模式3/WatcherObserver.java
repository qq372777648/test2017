package �۲���ģʽ3;
/**
 * ˮ�ʹ۲��߽ӿڶ���
 * @author Administrator
 *
 */
public interface WatcherObserver {
	/**
	 * ��֪ͨ�ķ���
	 * @param subject ���˱��۲��Ŀ�����
	 */
	public void update(WaterQualitySubject subject);
	/**
	 * ���ù۲���Ա��ְ��
	 * @param job �۲���Ա��ְ��
	 */
	public void setJob(String job);
	/**
	 * ��ȡ�۲���Ա��ְ��
	 * @return
	 */
	public String getJob();
	
	
	
}
