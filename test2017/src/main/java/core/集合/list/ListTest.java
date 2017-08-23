package core.集合.list;

import java.util.ArrayList;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月12日 下午1:49:36 
* @version:1.0
* @description:
*/
public class ListTest {
	public static void main(String[] args) {
		ArrayList<String > arr=new ArrayList<String>();
		arr.add("a");
		arr.add("b");
		arr.add("c");
		arr.add("d");
		
		
		System.out.println(arr.subList(0, 2));
	}

}
