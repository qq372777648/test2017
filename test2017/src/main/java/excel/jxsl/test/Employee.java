package excel.jxsl.test;

import java.util.Date;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年7月11日 下午3:37:43 
* @version:1.0
* @description:
*/
public class Employee {
    private String name;
    private int age;
    private Double weight;
    private Date birthDate;
    private Employee superior;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Employee getSuperior() {
		return superior;
	}
	public void setSuperior(Employee superior) {
		this.superior = superior;
	}
    
    
    

}
