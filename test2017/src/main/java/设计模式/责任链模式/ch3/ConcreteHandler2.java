package ������ģʽ.ch3;

public class ConcreteHandler2 extends Handler {
	private String  name;
	public ConcreteHandler2( String  name){
		this.name = name;
	}
	@Override
	public void handlerRequest(String condition) {
		//Ҫô����Ҫô���´�
		if (condition.equals(name)) {
			System.out.println(name+"����....");
		} else {
			System.out.println(name+"���´�...");
			////�践���¼Ҷ��������
			this.getSuccessor().handlerRequest(condition);
		}
	}

}
