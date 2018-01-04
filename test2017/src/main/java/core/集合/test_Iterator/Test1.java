package core.集合.test_Iterator;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


/** 
* @author: liangzhenwei
* @create：2017年11月7日 下午2:23:11 
*/
public class Test1 {
	public static void main(String[] args) {
		Set<String> values = new TreeSet<String>();
		values.add("dhello1");
		values.add("chello1");
		values.add("aworld1");
		
		 Iterator<String> iterator =values.iterator();  
		 while (iterator.hasNext()) {          
             String value = iterator.next();  
             System.out.println(value);
             iterator.remove();  //从set移除元素
         }
		 
		 System.out.println(values);
		 
		 Iterator<String> iterator2 =values.iterator();  
		 while (iterator2.hasNext()) {          
             String value = iterator2.next();  
             System.out.println(value);
//             iterator.remove();  
         } 
		 
	}

}
 