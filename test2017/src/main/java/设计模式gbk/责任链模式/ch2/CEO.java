package 责任链模式.ch2;

public class CEO extends Handler {

	  @Override
	  public boolean approve(double day) {
	      System.out.println("总经理审批通过");
	      return true; 
	  }
	}
