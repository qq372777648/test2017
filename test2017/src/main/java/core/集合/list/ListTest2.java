package core.集合.list;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xbean.finder.AnnotationFinder.SubArchive.E;

/** 
* @author: liangzhenwei
* @create：2018年1月23日 下午4:37:15 
* @company:广州荔支网络技术有限公司
* @description
*/
public class ListTest2 {
	public static void main(String[] args) {
		List<ListItem> list=new ArrayList<ListItem>();
		list.add(new ListItem("a",new Date()) );
		
//		list.set(index, element)
		ListItem item=list.get(0);
		item=new ListItem("b",new Date(System.currentTimeMillis()+1000000000));
		System.out.println(list);
		
		new ArrayList<E>().addAll(null);
	}

}
