package test;

import java.util.UUID;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年8月12日 下午2:12:46 
* @version:1.0
* @description:
*/
public class Sql拼接 {
	public static void main(String[] args) {
//		sysoutSqlIn();
		sysoutSqlOr();
		
		//结论  in 跟 or 差不多 ,or快一点点而已
	}
	
	public  static void sysoutSqlIn(){
		String base="SELECT OrderNo from PaymentRecord where OrderNo in ('";
		StringBuffer sbBuffer=new StringBuffer(base);
		for (int i = 0; i < 5000; i++) {
			sbBuffer.append(UUID.randomUUID().toString().replace("-", ""));
			sbBuffer.append("','");
		}
		sbBuffer.deleteCharAt(sbBuffer.length()-1);
		sbBuffer.deleteCharAt(sbBuffer.length()-1);
		sbBuffer.append(")");
		System.out.println(sbBuffer.toString());
	}
	
	public  static void sysoutSqlOr(){
		String base="SELECT OrderNo from PaymentRecord where ";
		StringBuffer sbBuffer=new StringBuffer(base);
		for (int i = 0; i < 10000; i++) {
			
			sbBuffer.append("OrderNo='");
			sbBuffer.append(UUID.randomUUID().toString().replace("-", ""));
			sbBuffer.append("' or ");
		}
		sbBuffer.deleteCharAt(sbBuffer.length()-1);
		sbBuffer.deleteCharAt(sbBuffer.length()-1);
		sbBuffer.deleteCharAt(sbBuffer.length()-1);
		sbBuffer.deleteCharAt(sbBuffer.length()-1);
		System.out.println(sbBuffer.toString());
	}


}
