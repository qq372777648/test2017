package 中间件.redis;

import java.io.Serializable;

/** 
* @author: lzw
* @email:372777648@qq.com
* @create：2017年6月20日 下午2:11:02 
* @version:1.0
* @description:
*/
public class RedisObj implements Serializable {
	private static final long serialVersionUID = 1L;
	private String a;
	private Integer i;
	private RedisObj nextObj;
	public RedisObj(String a, Integer i, RedisObj nextObj) {
		super();
		this.a = a;
		this.i = i;
		this.nextObj = nextObj;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public Integer getI() {
		return i;
	}
	public void setI(Integer i) {
		this.i = i;
	}
	public RedisObj getNextObj() {
		return nextObj;
	}
	public void setNextObj(RedisObj nextObj) {
		this.nextObj = nextObj;
	}
	

}
