package _1����ģʽ;

public class SingleTonObj {
	static private  SingleTonObj singleTonObj=null;
	private SingleTonObj(){
		System.out.println("��ֻ�ῴ����仰һ�Σ�heh");
	};
	
	static public SingleTonObj getInstance(){
		if(singleTonObj==null)
			singleTonObj=new SingleTonObj();
		return singleTonObj;
	}
	

}
