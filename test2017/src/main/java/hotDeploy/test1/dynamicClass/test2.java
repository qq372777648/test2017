package hotDeploy.test1.dynamicClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**   
* @author lzw   
* @date 2017年3月2日 上午10:13:46 
* @Description: 
* @version V1.0   
*/
public class test2 {
	public static void main(String[] args) {
		 try {
			Class clazz = Zhuangbi1.class;
			        Method method = clazz.getMethod("main", new Class[] { String[].class });
			        method.invoke(null, new String[] { null });
			        System.out.println();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
