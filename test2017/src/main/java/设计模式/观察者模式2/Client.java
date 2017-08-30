package 观察者模式2;

/**
 *测试类
 */
public class Client {
 public static void main(String[] args) {
    //创建被观察者对象
    ConcreteSubject subject = new ConcreteSubject();
    //创建观察者对象
    Observer observer = new ConcreteObserver();
    //将观察者对象注册到被观察者对象上
    subject.attach(observer);
    //改变被观察者的状态
    subject.change("今天好开心哦");
 }

}

