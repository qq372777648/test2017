package thirdpartyJar.solr;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SolrDelTest {  
	private final static String URL = "http://localhost:8983/solr/jcg";// 注意当多core时，要指定一个core，不然会报错
     static HttpSolrServer solrServer = new HttpSolrServer(URL);  
     /** 
      * 根据id删除 
      * @param id 
      */  
     public static void deleteById(String id) {  
            try {  
                  
                solrServer.deleteById(id+"");  
                solrServer.commit();  
            } catch (Exception e) {  
                 System.out.println("错误");  
            }  
        }  
          
        /** 
         * 删除所有文档，为安全起见，使用时再解注函数体 。 
         */  
        public static void deleteAll() {  
            try {  
                solrServer.deleteByQuery("*:*");  
                solrServer.commit();  
            } catch (Exception e) {  
                System.out.println("错误");  
            }  
        }  
    public static void main(String[] args) {  
//        deleteById("99");  
        deleteAll();  
    }  
}  