package 责任链模式.ch2;

public class ProjectManager extends Handler {

	@Override
	public boolean approve(double day) {
		if (day < 2) {
			System.out.println("项目经理审批通过");
			return true;
		} else {
			System.out.println("项目经理传给了他的上司");
			return getHandler().approve(day);
		}
	}

}
