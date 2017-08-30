package 动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorDemo {
	public static void main(String[] args) {
		//创建被代理对象
		CalculatorSuject calculator = new Calculator();
		//创建自动生成代理类的对象
		CreateProxy createProxy = new CreateProxy();
		//绑定代理对象，用createProxy生成一个代理对象。
		CalculatorSuject calProxy = (CalculatorSuject)createProxy.bind(calculator);
		
		calProxy.jia(3, 4);
//		calProxy.cheng(2, 3);
	}
}

interface CalculatorSuject{
	public int jia(int a,int b);
	public int jian(int a,int b);
	public void cheng(int a,int b);
	public void chu(int a,int b);
}
//需求在每个方法的前后都加上日志
class Calculator implements CalculatorSuject{

	@Override
	public int jia(int a, int b) {
		//方法前打日志
		int result = a + b;
		//方法后打日志
		return result;
	}

	@Override
	public int jian(int a, int b) {
		int result = a - b;
		return result;
	}

	@Override
	public void cheng(int a, int b) {
		System.out.println(a*b);
	}

	@Override
	public void chu(int a, int b) {
		System.out.println(a/b);
	}
	
}
/**
 * 定义动态生成代理对象的类
 */

class CreateProxy implements InvocationHandler {
	private Object subject;//代理的目标对象
	/**
	 * 绑定目标对象, 返回一个代理对象
	 * @param subject
	 * @return
	 */
	public Object bind(Object subject) {
		this.subject = subject;
		return Proxy.newProxyInstance(this.subject.getClass().getClassLoader(), this.subject.getClass().getInterfaces(), this);
	}
	 /**
	   *被代理类执行方法之前会执行该方法
	   *proxy 在其上调用方法的代理示例
	   *method 对应于在代理示例上调用的接口方法的Method 实例
	   * args 参数
	  */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("方法前打日志........");
		Object result = method.invoke(this.subject, args);
		System.out.println(result);
		System.out.println("方法后打日志........");
		return result;
	}
	
}

