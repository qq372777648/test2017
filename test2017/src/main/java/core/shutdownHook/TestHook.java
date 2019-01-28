package core.shutdownHook;
/** 
* @author: liangzhenwei
* @create：2018年3月9日 下午5:33:09 
* @company:广州荔支网络技术有限公司
* @description 被kill (kill -9 则无法执行)
*/
public class TestHook {
	public static void main(String[] args) {
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("收尾工作做完");
			}
		};
        Runtime.getRuntime().addShutdownHook(t);
        
        try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
