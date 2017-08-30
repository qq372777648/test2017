package 观察者模式;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者类
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
		// //推模型接收
		// System.out.println(name+"收到了博主的文件，可以去阅读了.....");
		// Article article = (Article)arg;
		// System.out.println("标题为："+article.getTitle());
		// System.out.println("内容为："+article.getContent());

		// 拉模型接收
		System.out.println(name + "收到了博主的文件，可以去阅读了.....");
		BlogUser blogUser = (BlogUser) o;
		System.out.println("标题为：" + blogUser.getArticle().getTitle());
		System.out.println("内容为：" + blogUser.getArticle().getContent());
	}

}
