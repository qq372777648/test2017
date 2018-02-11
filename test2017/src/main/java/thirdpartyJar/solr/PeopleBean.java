package thirdpartyJar.solr;
import java.util.List;  

import org.apache.solr.client.solrj.beans.Field;  
  
public class PeopleBean {  
  
    private String id;  
    private String name;  
    private String[] content;//多值，对应 multiValued="true"  
      
      
      
    public String getId() {  
        return id;  
    }  
    @Field  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    @Field("name")  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    public String[] getContent() {  
        return content;  
    }  
    @Field  
    public void setContent(String[] content) {  
        this.content = content;  
    }  
    public PeopleBean(){//此处应该注意，当QueryResponse.getBeans(PeopleBean.class);方式查询，并返回实体bean时，必须有一个空的构造方法  
          
    }  
    public PeopleBean(String id, String name, String[] content) {  
        this.id = id;  
        this.name = name;  
        this.content = content;  
    }  
      
  
  
      
      
}  
