package װ��ģʽ;
//�����ж���̬���һЩ����Ĺ��ܡ�
public class Person {
	public void chiFan(){
		System.out.println("�Է�");
	}
	/**
	 * ����Ҫ�����ж�����й�����ǿʱ�����Զ����࣬�����ж����룬�������ж�����й�����ǿ��
  ��ô�����Զ�������Ϊ��װ���ࡣ��MyBufferedReader Supperperson������IO���еģ�InputStreamReader BufferedReader  ��
  װ����ͨ����ͨ�����췽�����ձ�װ�εĶ��󣬲����ڱ�װ�εĶ����ܽ�����ǿ��
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Person person = new Person();//��װ����

		SupperPerson supperPerson = new SupperPerson(person);//װ����
		supperPerson.chiFanAndHappy();
		
		
	}
}

class SupperPerson{
	private Person person;
	public  SupperPerson(Person person) {
		this.person = person;
	}
	public void chiFanAndHappy() {
		System.out.println("---�Ȳ�");
		
		person.chiFan();
		
		System.out.println("---��K");
		System.out.println("---��һ����");
	}
}
