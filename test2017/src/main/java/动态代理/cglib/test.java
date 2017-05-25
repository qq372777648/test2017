package 动态代理.cglib;
/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月25日 下午4:22:05 
* @version:1.0
* @description:
*/
public class test {
	public static void main(String[] args) {
//		CGLibDynamicProxy.getInstance().getProxy(Dog.class).run();
		CGLibDynamicProxy.proxy(Dog.class).run();
	}

}
