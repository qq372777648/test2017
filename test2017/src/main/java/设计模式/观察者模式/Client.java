package 观察者模式;

public class Client {
	public static void main(String[] args) {
		BlogUser blogUser = new BlogUser();//博主（被观察者）
		MyObserver reader1 = new MyObserver("张三");//阅读者（观察者）
		MyObserver reader2 = new MyObserver("李四");//阅读者（观察者）
		blogUser.addObserver(reader1);//把阅读者注册到被观察者中
		blogUser.addObserver(reader2);//把阅读者注册到被观察者中
		blogUser.publishBlog("好消息", "快过年了。。。。");
	}
}
