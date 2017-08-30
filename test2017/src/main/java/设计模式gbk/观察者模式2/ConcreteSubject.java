package 观察者模式2;

/**
 * 具体被观察者
 */
public class ConcreteSubject extends Subject{
 private String state;
 public String getState() {
 return state;
 }
 public void change(String newState){
 state = newState;
 System.out.println("被观察者改变的状态为：" + state);
 //状态发生改变，通知各个观察者
 this.nodifyObservers(state);
 }
}