package �۲���ģʽ2;

/**
 * ���屻�۲���
 */
public class ConcreteSubject extends Subject{
 private String state;
 public String getState() {
 return state;
 }
 public void change(String newState){
 state = newState;
 System.out.println("���۲��߸ı��״̬Ϊ��" + state);
 //״̬�����ı䣬֪ͨ�����۲���
 this.nodifyObservers(state);
 }
}