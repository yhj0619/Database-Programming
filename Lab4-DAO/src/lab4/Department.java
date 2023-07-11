package lab4;

public class Department {
	private int deptNo;
	private String dname;
	private int mgrNo;
	private int numOfEmps;
	
	public Department(int deptNo, String dname, int mgrNo, int numOfEmps) {
		super();
		this.deptNo = deptNo;
		this.dname = dname;
		this.mgrNo = mgrNo;
		this.numOfEmps = numOfEmps;
	}
	
	public Department() {
		super();
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getMgrNo() {
		return mgrNo;
	}

	public void setMgrNo(int mgrNo) {
		this.mgrNo = mgrNo;
	}

	public int getNumOfEmps() {
		return numOfEmps;
	}

	public void setNumOfEmps(int numOfEmps) {
		this.numOfEmps = numOfEmps;
	}
	
	
}
