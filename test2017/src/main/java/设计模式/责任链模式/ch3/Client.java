package ������ģʽ.ch3;

public class Client {
	public static void main(String[] args) {
		//�������崦����
		ConcreteHandler1 concreteHandler1 = new ConcreteHandler1("����");
		ConcreteHandler2 concreteHandler2 = new ConcreteHandler2("����");
		ConcreteHandler3 concreteHandler3 = new ConcreteHandler3("����");
		ConcreteHandlerN concreteHandlerN = new ConcreteHandlerN("����");
		//���������� ����˭����һ������ô��һ������˭
		concreteHandler1.setSuccessor(concreteHandler2);//��̬��
		concreteHandler2.setSuccessor(concreteHandler3);
		concreteHandler3.setSuccessor(concreteHandlerN);
		
		concreteHandler1.handlerRequest("����");
		
	}
}
