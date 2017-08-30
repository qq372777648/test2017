package ��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorDemo {
	public static void main(String[] args) {
		//�������������
		CalculatorSuject calculator = new Calculator();
		//�����Զ����ɴ�����Ķ���
		CreateProxy createProxy = new CreateProxy();
		//�󶨴��������createProxy����һ���������
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
//������ÿ��������ǰ�󶼼�����־
class Calculator implements CalculatorSuject{

	@Override
	public int jia(int a, int b) {
		//����ǰ����־
		int result = a + b;
		//���������־
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
 * ���嶯̬���ɴ���������
 */

class CreateProxy implements InvocationHandler {
	private Object subject;//�����Ŀ�����
	/**
	 * ��Ŀ�����, ����һ���������
	 * @param subject
	 * @return
	 */
	public Object bind(Object subject) {
		this.subject = subject;
		return Proxy.newProxyInstance(this.subject.getClass().getClassLoader(), this.subject.getClass().getInterfaces(), this);
	}
	 /**
	   *��������ִ�з���֮ǰ��ִ�и÷���
	   *proxy �����ϵ��÷����Ĵ���ʾ��
	   *method ��Ӧ���ڴ���ʾ���ϵ��õĽӿڷ�����Method ʵ��
	   * args ����
	  */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("����ǰ����־........");
		Object result = method.invoke(this.subject, args);
		System.out.println(result);
		System.out.println("���������־........");
		return result;
	}
	
}

