package excel.jxsl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年7月11日 下午3:25:25 
* @version:1.0
* @description: un pass
*/
public class JxslTest1 {
	public static void main(String[] args) throws IOException  {
	    List<Employee> employees = new ArrayList<Employee>();
	    for (int i = 0; i < 3; i++) {
	    	Employee e= new Employee();
	    	e.setAge(18+i);
	    	e.setName("btfgril"+i);
	    	e.setBirthDate(new Date());
	    	e.setWeight(52.14);
	    	employees.add(e);
		}
	    String path=JxslTest1.class.getClassLoader().getResource("test.xlsx").getPath();
	    System.out.println(path);
	    
//	    InputStream is = JxslTest1.class.getResourceAsStream("test.xlsx"); 
	    
	    
	    InputStream is=new FileInputStream(new File(path));   
	    OutputStream os = new FileOutputStream("g://2.xlsx");
        Context context = new Context();
        context.putVar("employees", employees);
        JxlsHelper.getInstance().processTemplate(is, os, context);
	}

}
