package �۲���ģʽ3;

public class Client {
	public static void main(String[] args) {
		//����ˮ�ʱ��۲��߶���
		WaterQuality waterQuality = new WaterQuality();
		//���������۲��߶���
		WatcherObserver watcher1 = new Watcher();
		watcher1.setJob("�����Ա");
		WatcherObserver watcher2 = new Watcher();
		watcher2.setJob("Ԥ����Ա");
		WatcherObserver watcher3 = new Watcher();
		watcher3.setJob("��ⲿ���쵼");
		
		//ע��۲���
		waterQuality.attach(watcher1);
		waterQuality.attach(watcher2);
		waterQuality.attach(watcher3);
		//��дˮ�ʱ���
		System.out.println("��ǰˮ��Ϊ������ʱ��----------------------------");
		waterQuality.setPolluteLevel(0);//�������ˮ��
		System.out.println("��ǰˮ��Ϊ�����Ⱦ��ʱ��----------------------------");
		waterQuality.setPolluteLevel(1);
		System.out.println("��ǰˮ��Ϊ�жȵ�ʱ��----------------------------");
		waterQuality.setPolluteLevel(2);
		
	}
}
