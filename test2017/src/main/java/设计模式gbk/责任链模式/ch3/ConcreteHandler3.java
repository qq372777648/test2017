package 责任链模式.ch3;

public class ConcreteHandler3 extends Handler {
	private String  name;
	public ConcreteHandler3( String  name){
		this.name = name;
	}
	@Override
	public void handlerRequest(String condition) {
		//要么做，要么往下传
		if (condition.equals(name)) {
			System.out.println(name+"处理....");
		} else {
			System.out.println(name+"往下传...");
			////设返回下家对象的引用
			this.getSuccessor().handlerRequest(condition);
		}
		
	}

}
