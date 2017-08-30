package 责任链模式.ch3;

public class Client {
	public static void main(String[] args) {
		//创建具体处理者
		ConcreteHandler1 concreteHandler1 = new ConcreteHandler1("张三");
		ConcreteHandler2 concreteHandler2 = new ConcreteHandler2("李四");
		ConcreteHandler3 concreteHandler3 = new ConcreteHandler3("王五");
		ConcreteHandlerN concreteHandlerN = new ConcreteHandlerN("陈六");
		//构建责任链 设置谁是下一个，那么下一个就是谁
		concreteHandler1.setSuccessor(concreteHandler2);//多态性
		concreteHandler2.setSuccessor(concreteHandler3);
		concreteHandler3.setSuccessor(concreteHandlerN);
		
		concreteHandler1.handlerRequest("张三");
		
	}
}
