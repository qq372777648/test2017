package core.集合.list;

import java.util.Date;

/** 
* @author: liangzhenwei
* @create：2018年1月23日 下午4:37:27 
* @company:广州荔支网络技术有限公司
* @description
*/
public class ListItem {
	public String a;
	public Date date;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ListItem(String a, Date date) {
		super();
		this.a = a;
		this.date = date;
	}
	@Override
	public String toString() {
		return "ListItem [a=" + a + ", date=" + date + "]";
	}
	
	

}
