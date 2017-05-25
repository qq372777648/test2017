package test2017.jvm.classloader.加载测试.bean;

import test2017.jvm.classloader.加载测试.annotation.Inject;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月22日 下午3:31:44 
* @version:1.0
* @description:
*/

public class StaticCodeBean {
	@Inject
	public String str;
	private Integer i;
	
	
	static {
		System.out.println("StaticCodeBean static-code execute");
	}
	{
		System.out.println("StaticCodeBean common-code execute");
	}
	public StaticCodeBean() {
		System.out.println("StaticCodeBean constructor-code execute");
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	

}
