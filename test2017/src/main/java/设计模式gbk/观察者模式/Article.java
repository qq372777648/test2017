package �۲���ģʽ;

public class Article {
	private String title;//���±���
	
	private String Content;//��������

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
