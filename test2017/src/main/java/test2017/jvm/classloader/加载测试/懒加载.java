package test2017.jvm.classloader.加载测试;

import java.lang.reflect.Field;

import test2017.jvm.classloader.加载测试.annotation.Inject;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月22日 下午3:30:25 
* @version:1.0
* @description:
*/
public class 懒加载 {
	public static void main(String[] args) throws ClassNotFoundException {
		//不初始化类（un execute the static code）
		Class cls=Class.forName("test2017.jvm.classloader.加载测试.bean.StaticCodeBean", false, Thread.currentThread().getContextClassLoader());
		Field flds[]=cls.getDeclaredFields();	
		for (int i = 0; i < flds.length; i++) {
			System.out.println(flds[i].getName());
			if(flds[i].getAnnotation(Inject.class) != null)
				System.out.println(flds[i].getAnnotation(Inject.class));;
			
		}
		//初始化类
		Class.forName("test2017.jvm.classloader.加载测试.bean.StaticCodeBean", true, Thread.currentThread().getContextClassLoader());
	}

}
