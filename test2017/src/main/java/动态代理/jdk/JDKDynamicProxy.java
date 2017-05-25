package 动态代理.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月25日 下午3:53:34 
* @version:1.0
* @description:
*/
public class JDKDynamicProxy implements InvocationHandler {
	private Object target;
	

	public JDKDynamicProxy(Object target) {
		this.target = target;
	}


	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("---------前置动作");
		Object result=method.invoke(target, args);
		System.out.println("---------后置动作");
		return result;
	}
	
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}
