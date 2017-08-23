package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年6月23日 上午11:33:45 
* @version:1.0
* @description:
*/
public class Test1 {
	public static void main(String[] args) throws ParseException {
		System.out.print("  aa bb c ".trim());
		System.out.print("aaa");
		
		
		String strs[]="asdf.jpg".split("\\.");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
		
		String s="123";
		System.out.println(s.substring(1,2));
		
		System.out.println(isDate("2017-01-01"));
		
		
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 format.setLenient(false);
		 System.out.println(format.format(format.parse(null)));
	}
	
	
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
