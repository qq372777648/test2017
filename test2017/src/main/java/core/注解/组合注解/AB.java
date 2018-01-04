package core.注解.组合注解;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author: liangzhenwei
* @create：2018年1月4日 上午11:19:53 
* @company:广州荔支网络技术有限公司
*/

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME) 
@Target({ ElementType.TYPE})
@A(value = "avalue")
@B
public @interface AB {
	public String value() default "AB msg"; 
	 String value2() default "AB msg2"; 
}
