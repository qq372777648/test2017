package ������ģʽ.ch3;

public class ConcreteHandlerN extends Handler {
	private String  name;
	public ConcreteHandlerN( String  name){
		this.name = name;
	}
	@Override
	public void handlerRequest(String condition) {
		System.out.println(name+"����....");
	}

}
