package �۲���ģʽ;

import java.util.Observable;
import java.util.Observer;

/**
 * �۲�����
 * 
 */
public class MyObserver implements Observer {
	private String name;
	public MyObserver(String name) {
		super();
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		// //��ģ�ͽ���
		// System.out.println(name+"�յ��˲������ļ�������ȥ�Ķ���.....");
		// Article article = (Article)arg;
		// System.out.println("����Ϊ��"+article.getTitle());
		// System.out.println("����Ϊ��"+article.getContent());

		// ��ģ�ͽ���
		System.out.println(name + "�յ��˲������ļ�������ȥ�Ķ���.....");
		BlogUser blogUser = (BlogUser) o;
		System.out.println("����Ϊ��" + blogUser.getArticle().getTitle());
		System.out.println("����Ϊ��" + blogUser.getArticle().getContent());
	}

}
