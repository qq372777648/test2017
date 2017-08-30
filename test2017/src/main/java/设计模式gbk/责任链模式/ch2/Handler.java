package 责任链模式.ch2;

/**
 * 描述：审批处理抽象类
 */
public abstract class Handler {
	protected Handler handler;
	/**
	 * 描述：审批
	 */
	public abstract boolean approve(double day);

	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}