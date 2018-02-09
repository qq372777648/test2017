package jdbc;

import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Connection;  
import java.sql.Statement; 

/** 
* @author: liangzhenwei
* @create：2018年1月25日 下午7:10:19 
* @company:广州荔支网络技术有限公司
* @description
*/
public class TestConnect {
	public static void main(String[] args) throws SQLException {
		System.out.println( "Hello World!" );  
        Connection conn = null;  
        String sql;  
          
        String conn_str = "jdbc:mysql://139.162.80.167:3307/interesting?"  
                + "user=test&password=lzwhlh&useUnicode=true&characterEncoding=UTF8";  
        try {  
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动   
            System.out.println("成功加载MySQL驱动程序");  
              
            // 一个Connection代表一个数据库连接  
            conn = DriverManager.getConnection(conn_str);  
            Statement stmt = conn.createStatement();  
            sql = "show tables;";  
            ResultSet result = stmt.executeQuery(sql);  
            if (result != null) {  
                while (result.next()) {  
                    System.out.println(result.getString(1) + "\t");  
                }  
            }  
        } catch (SQLException e) {  
            System.out.println("MySQL操作错误");  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            conn.close();  
        }  
  
	}

}
