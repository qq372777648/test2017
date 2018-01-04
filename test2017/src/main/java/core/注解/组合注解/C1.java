package core.注解.组合注解;

import java.util.Arrays;

/** 
* @author: liangzhenwei
* @create：2018年1月4日 上午11:29:35 
* @company:广州荔支网络技术有限公司
*/
@AB
//@B
public class C1 {
//	@AB
	public String f;
	
//	@AB
	public String method( int param){
		return "";
	}
	
	public static void main(String[] args) {
		
		
		System.out.println( Arrays.asList(C1.class.getAnnotation(AB.class).annotationType().getAnnotations())  );
//		System.out.println(C1.class.getAnnotation(AB.class).getClass().getAnnotations());
//		System.out.println(C1.class.getAnnotation(AB.class).getClass().getAnnotations().length);
	}

}
