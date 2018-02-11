package thirdpartyJar.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;  
  
//ikanalyzer5.5.x
//https://github.com/EugenePig/ik-analyzer-solr5
public class SolrAddOrUpdateTest {  
  
    private final static String URL = "http://localhost:8983/solr/jcg";  
    
    public static void main(String[] args) throws Exception {  
    	add();  
    	addList();
    	addBeans() ;
    }  
    public static void  add(){  
          
        //1、创建SolrServer对象，该对象有两个可以使用，都是线程安全的  
//      HttpSolrServer：启动web服务器使用的，通过http请求的  
//      EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了  
        try {  
            HttpSolrServer server = new HttpSolrServer(URL);  
            //把查询出来的数据全部删除  
//          server.deleteByQuery("*:*");  
//          server.commit();  
              
            SolrInputDocument doc = new SolrInputDocument();  
            //id是必填的，并且是String类型的  
            //<field name="id" type="string" indexed="true" stored="true" required="true" />  
            //id是唯一的主键，当多次添加的时候，最后添加的相同id会覆盖前面的域  
            doc.addField("id", "99");  
            doc.addField("name", "灵光一闪而过");  
            doc.addField("content", "我想了很久，大概3秒，然后说：你怎么不去死");  
            server.add(doc);  
            server.commit();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (SolrServerException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 基于列表的添加 
     * @throws SolrServerException 
     * @throws IOException 
     */  
    public static void addList() throws SolrServerException, IOException{  
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();  
        SolrInputDocument doc = new SolrInputDocument();  
        doc.addField("id", "15");  
        doc.addField("name", "很好，solr可以工作了");  
        doc.addField("content", "solr总算可以正式工作了");  
          
        docs.add(doc);  
          
        doc = new SolrInputDocument();  
        doc.addField("id", "13");  
        doc.addField("name", "测试以下solr的添加");  
        doc.addField("content", "看看能不能添加一个列表信息");  
          
        docs.add(doc);  
          
        HttpSolrServer server = new HttpSolrServer(URL);  
        server.add(docs);  
        server.commit();  
    }  
      
    /** 
     * 基于javabean的添加 
     * @throws SolrServerException 
     * @throws IOException 
     */  
    public static void addBeans() throws SolrServerException, IOException{  
        List<PeopleBean> msgs = new ArrayList<PeopleBean>();  
        //多值域的添加使用数组  
        msgs.add(new PeopleBean("19","基于javabean的添加9",new String[]{"这是content9","这是dddddddddcontent9"} ));  
        msgs.add(new PeopleBean("20","基于javabean的列表数据的添加10", new String[]{"solr这是content10","这是conteooooooooont10"}));  
          
        HttpSolrServer server = new HttpSolrServer(URL);  
        server.addBeans(msgs);  
        server.commit();  
    }  
   
}  