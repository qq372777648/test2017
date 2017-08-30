package 观察者模式;

import java.util.Observable;
import java.util.Observer;
/**
 * 被观察者类
 *
 */
public class BlogUser extends Observable{
	private Article article;
	public void publishBlog(String title, String content) {
	    article = new Article(title, content);
		System.out.println("博主发表文章，文章标题是："+article.getTitle()+",文章内容是："+article.getContent());
		this.setChanged();//表明被观察者改变了。
		//this.notifyObservers(article);//通知所有观察者,推模型
		this.notifyObservers();//拉模型(不传article了，观察者要什么，自己拉吧)
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
}
