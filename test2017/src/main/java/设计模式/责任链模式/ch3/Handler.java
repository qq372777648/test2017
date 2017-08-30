package 责任链模式.ch3;
/**
 * 抽象处理者（Handler）角色 ：定义一个请求的接口。如果需要可以定义个一个方法用来设定和返回下家对象的引用，多态性
 * @author Administrator
 *
 */
public abstract class Handler {
	public Handler successor;
	//设返回下家对象的引用
	public Handler getSuccessor() {
		return successor;
	}
	//设定下家对象的引用
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
	public abstract void handlerRequest(String condition) ;
	
}
