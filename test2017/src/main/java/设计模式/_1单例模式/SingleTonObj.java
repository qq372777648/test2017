package _1单例模式;

public class SingleTonObj {
	static private  SingleTonObj singleTonObj=null;
	private SingleTonObj(){
		System.out.println("你只会看到这句话一次！heh");
	};
	
	static public SingleTonObj getInstance(){
		if(singleTonObj==null)
			singleTonObj=new SingleTonObj();
		return singleTonObj;
	}
	

}
