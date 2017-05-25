package hotDeploy.test1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**   
* @author lzw   
* @date 2017年3月2日 上午9:58:21 
* @Description: test 类
* @version V1.0   
*/
public class ServerHotDeployApi {
	public static void main(String[] args) {
		try {
//			InputStream is = new FileInputStream("E:/old-dfim-pc/e/workspace/javaBreakthrough3/build/classes/hotDeploy/test1/dynamicClass/Zhuangbi1.class");
			
			InputStream is = new FileInputStream("F:/github_project/back/java/test2017/test2017/target/classes/hotDeploy/test1/dynamicClass/Zhuangbi1.class");
			byte[] b = new byte[is.available()];
			is.read(b);
			is.close();
			
			System.out.println(JavaClassExecuter.execute(b));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
