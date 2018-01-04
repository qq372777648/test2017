package core.注解.组合注解;

import java.lang.annotation.Inherited;

/** 
* @author: liangzhenwei
* @create：2018年1月4日 上午11:45:15 
* @company:广州荔支网络技术有限公司
*/
public class C2 extends C1{
	public static void main(String[] args) {
		//@Inherited
		System.out.println(C2.class.getAnnotation(B.class));
	}

}
