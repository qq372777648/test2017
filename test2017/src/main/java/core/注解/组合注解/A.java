package core.注解.组合注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liangzhenwei
 * @create：2018年1月4日 上午11:19:36
 * @company:广州荔支网络技术有限公司
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface A {
	public String value();
	public String message() default "A msg"; 
    String[] constants() default {"1","2","3"};  //注解指定参数值集合   eg；{"1","2","3"}
}
