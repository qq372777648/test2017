package �۲���ģʽ;

import java.util.Observable;
import java.util.Observer;
/**
 * ���۲�����
 *
 */
public class BlogUser extends Observable{
	private Article article;
	public void publishBlog(String title, String content) {
	    article = new Article(title, content);
		System.out.println("�����������£����±����ǣ�"+article.getTitle()+",���������ǣ�"+article.getContent());
		this.setChanged();//�������۲��߸ı��ˡ�
		//this.notifyObservers(article);//֪ͨ���й۲���,��ģ��
		this.notifyObservers();//��ģ��(����article�ˣ��۲���Ҫʲô���Լ�����)
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
}
