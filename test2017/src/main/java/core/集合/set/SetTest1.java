package core.集合.set;

import java.util.HashSet;
import java.util.Set;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月11日 上午10:47:10 
* @version:1.0
* @description:
*/
public class SetTest1 {

	public static void main(String[] args) {
		Set<String> values = new HashSet<String>();
		values.add("hello1");
		values.add("hello1");
		values.add("world1");
		System.out.println(values);
	}
}
