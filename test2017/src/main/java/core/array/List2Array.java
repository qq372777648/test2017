package core.array;

import java.util.ArrayList;
import java.util.List;

import org.apache.xbean.finder.AnnotationFinder.SubArchive.E;

/** 
* @author: liangzhenwei
* @create：2017年12月6日 下午2:50:01 
* @company:广州荔支网络技术有限公司
*/
public class List2Array {
	public static void main(String[] args) {
		List imgUrls=new ArrayList<String>();
		imgUrls.add("1");
		imgUrls.add("12");
		imgUrls.add("14");
		String []aa=(String[])imgUrls.toArray(new String[imgUrls.size()]);
		System.out.println(aa[2]);
	}

}
