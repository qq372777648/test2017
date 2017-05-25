package 动态代理.cglib;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年5月25日 下午4:09:41 
* @version:1.0
* @description:
*/
public class CGLibDynamicProxy implements MethodInterceptor {  
	
	private static CGLibDynamicProxy instance=new CGLibDynamicProxy();
    private CGLibDynamicProxy() {
	}
    
    public static CGLibDynamicProxy getInstance(){
    	return instance;
    }
    
    
    public <T> T getProxy(Class<T> cls){
    	return (T) Enhancer.create(cls, this);
    }
    
    public static <T> T proxy(Class<T> cls){
    	return (T) Enhancer.create(cls, instance);
    }

      
    // 回调方法  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("事物开始");  
        proxy.invokeSuper(obj, args);  
        System.out.println("事物结束");  
        return null;  
  
  
    }  

//	private Object target;  
//  
//    /** 
//     * 创建代理对象 
//     *  
//     * @param target 
//     * @return 
//     */  
//    public Object getInstance(Object target) {  
//        this.target = target;  
//        Enhancer enhancer = new Enhancer();  
//        enhancer.setSuperclass(this.target.getClass());  
//        // 回调方法  
//        enhancer.setCallback(this);  
//        // 创建代理对象  
//        return enhancer.create();  
//    }  
//  
//    @Override  
//    // 回调方法  
//    public Object intercept(Object obj, Method method, Object[] args,  
//            MethodProxy proxy) throws Throwable {  
//        System.out.println("事物开始");  
//        proxy.invokeSuper(obj, args);  
//        System.out.println("事物结束");  
//        return null;  
//  
//  
//    }  
  
}  
