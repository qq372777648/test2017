package �۲���ģʽ2;


/**
 *����۲���
 */
public class ConcreteObserver implements Observer {
 //�۲��ߵ�״̬
 private String observerState;
 @Override
 public void update(String state) {
    /**
     * ���¹۲��ߵ�״̬��ʹ����Ŀ���״̬����һ��
     */
    observerState = state;
    System.out.println("�۲��߹۲쵽��״̬Ϊ��"+observerState);
 }
}

