package ������ģʽ.ch3;
/**
 * �������ߣ�Handler����ɫ ������һ������Ľӿڡ������Ҫ���Զ����һ�����������趨�ͷ����¼Ҷ�������ã���̬��
 * @author Administrator
 *
 */
public abstract class Handler {
	public Handler successor;
	//�践���¼Ҷ��������
	public Handler getSuccessor() {
		return successor;
	}
	//�趨�¼Ҷ��������
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
	public abstract void handlerRequest(String condition) ;
	
}
