package �۲���ģʽ2;

/**
 *������
 */
public class Client {
 public static void main(String[] args) {
    //�������۲��߶���
    ConcreteSubject subject = new ConcreteSubject();
    //�����۲��߶���
    Observer observer = new ConcreteObserver();
    //���۲��߶���ע�ᵽ���۲��߶�����
    subject.attach(observer);
    //�ı䱻�۲��ߵ�״̬
    subject.change("����ÿ���Ŷ");
 }

}

