package 责任链模式.ch2;
/**
 *描述：测试类，首先来创建责任链，然后发出请求模拟员工来请假
 */
public class Client {

  public static void main(String[] args) {

    //创建节点
    GroupLeader gl = new GroupLeader();
    ProjectManager pm = new ProjectManager();
    DepartmentManager dm = new DepartmentManager();
    CEO ceo = new CEO();
    //建立责任链
    gl.setHandler(pm);
    pm.setHandler(dm);
    dm.setHandler(ceo);
    
    //向小组长发出申请，请求审批4天的假期
    gl.approve(2D);
    

  }

}