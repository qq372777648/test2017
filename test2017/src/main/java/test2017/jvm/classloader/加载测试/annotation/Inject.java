package test2017.jvm.classloader.加载测试.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月22日 下午3:41:35 
* @version:1.0
* @description:
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
