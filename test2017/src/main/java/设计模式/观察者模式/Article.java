package 观察者模式;

public class Article {
	private String title;//文章标题
	
	private String Content;//文章内容

	public Article() {
		super();
	}

	public Article(String title, String content) {
		super();
		this.title = title;
		Content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
