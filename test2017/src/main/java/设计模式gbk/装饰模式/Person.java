package 装饰模式;
//对已有对象动态添加一些额外的功能。
public class Person {
	public void chiFan(){
		System.out.println("吃饭");
	}
	/**
	 * 当想要对已有对象进行功能增强时，可以定义类，将已有对象传入，基于已有对象进行功能增强。
  那么我们自定义的类称为：装饰类。（MyBufferedReader Supperperson）还有IO流中的：InputStreamReader BufferedReader  等
  装饰类通常会通过构造方法接收被装饰的对象，并基于被装饰的对象功能进行增强。
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Person person = new Person();//被装饰者

		SupperPerson supperPerson = new SupperPerson(person);//装饰者
		supperPerson.chiFanAndHappy();
		
		
	}
}

class SupperPerson{
	private Person person;
	public  SupperPerson(Person person) {
		this.person = person;
	}
	public void chiFanAndHappy() {
		System.out.println("---喝茶");
		
		person.chiFan();
		
		System.out.println("---唱K");
		System.out.println("---来一根烟");
	}
}
