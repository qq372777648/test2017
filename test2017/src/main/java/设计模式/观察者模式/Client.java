package �۲���ģʽ;

public class Client {
	public static void main(String[] args) {
		BlogUser blogUser = new BlogUser();//���������۲��ߣ�
		MyObserver reader1 = new MyObserver("����");//�Ķ��ߣ��۲��ߣ�
		MyObserver reader2 = new MyObserver("����");//�Ķ��ߣ��۲��ߣ�
		blogUser.addObserver(reader1);//���Ķ���ע�ᵽ���۲�����
		blogUser.addObserver(reader2);//���Ķ���ע�ᵽ���۲�����
		blogUser.publishBlog("����Ϣ", "������ˡ�������");
	}
}
