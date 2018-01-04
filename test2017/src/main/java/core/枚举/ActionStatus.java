package core.枚举; 
/** 
* @author: liangzhenwei
* @create：2017年11月24日 下午7:04:39 
*/
public enum ActionStatus{
	SUCC(0, "成功。"),
	FAIL(1, "失败。"),
	TO_LOGIN(101, "重登录");
	
	final public static int SUCC_CODE=SUCC.getCode();
	final public static int FAIL_CODE=FAIL.getCode();
	

	private int code;
	private String message;
	
	ActionStatus(int code, String message) {
		System.out.println(message);
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return this.code;
	}
	public String getMessage() {
		return this.message;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}