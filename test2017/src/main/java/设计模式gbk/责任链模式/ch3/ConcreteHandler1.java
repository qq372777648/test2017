package ������ģʽ.ch3;

public class ConcreteHandler1 extends Handler {
	private String  name;
	public ConcreteHandler1( String  name){
		this.name = name;
	}
	@Override
	public void handlerRequest(String condition) {
		//Ҫô����Ҫô���´�
		if (condition.equals(name)) {
			System.out.println(name+"����....");
		} else {
			////�践���¼Ҷ��������
			System.out.println(name+"���´�...");
			this.getSuccessor().handlerRequest(condition);
		}
		
	}

}
