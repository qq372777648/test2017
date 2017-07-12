package excel.jxsl.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;


/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年7月12日 上午9:40:14 
* @version:1.0
* @description: pass
*/
public class Test2 {
	public static void main(String[] args) {
		exportChargeCount( ) ;
	}

	public static void exportChargeCount( ) 
    {
//        String templateFile = request.getSession().getServletContext().getRealPath("/")+"/doc/reportTmp/billingInfo.xls";
        Date date = new Date();
        String buildName = "xxxx";
        String fileName = buildName+"("+2002+")客户汇总报表.xls";
        List<Employee> employees =  new ArrayList<Employee>();
	    for (int i = 0; i < 3; i++) {
	    	Employee e= new Employee();
	    	e.setAge(18+i);
	    	e.setName("btfgril"+i);
	    	e.setBirthDate(new Date());
	    	e.setWeight(52.14);
	    	employees.add(e);
		}
        Map<String, Object> context = new HashMap<String, Object>();
        XLSTransformer transformer = new XLSTransformer();
         
        double sumMoney = 0;
        for (Employee Employee : employees) {
            sumMoney += Employee.getWeight();
        }
         
        context.put("employees", employees);
        context.put("sumMoney", sumMoney);
        
        
        try {
//            response.setContentType("application/vnd.ms-excel");  
//            response.setHeader("Content-disposition", "attachment;filename="+new String( fileName.getBytes("GBK"), "ISO8859-1" ));
//            Workbook workbook =  transformer.transformXLS(new FileInputStream(templateFile), context);
//            OutputStream ouputStream = response.getOutputStream();
//            workbook.write(ouputStream);  
//            ouputStream.flush();  
//            ouputStream.close(); 
        	String templateFile=Test2.class.getClassLoader().getResource("test3.xlsx").getPath();
            Workbook workbook =  transformer.transformXLS(new FileInputStream(templateFile), context);
            OutputStream out = new FileOutputStream(  "g://3.xlsx");
            workbook.write(out);  
            out.flush();  
            out.close();  
            
        } catch (ParsePropertyException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
