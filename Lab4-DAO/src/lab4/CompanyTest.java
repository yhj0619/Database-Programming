package lab4;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CompanyTest {

	private static CompanyDao compDao = new CompanyDao();
	
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		
		Department dept = compDao.findDeptInfo(deptName);	
		
		// dept 객체의 필드 값 출력 ...
		System.out.println("<부서정보>");
		if(dept != null) {
			System.out.println("부서번호: " + dept.getDeptNo());
			System.out.println("부서명: " + dept.getDname());
			System.out.println("관리자사번: " + dept.getMgrNo());
			System.out.println("사원수: " + dept.getNumOfEmps());
			System.out.println();
		} else {
			System.out.println("부서를 찾지 못했습니다.");
			return;
		}
		
	    List<Employee> empList = compDao.findEmployeesInDept(dept.getDeptNo()/* 부서번호 */); 
	    
		// empList에 포함된 모든 객체들의 필드 값 출력 ...
		// empList에 포함된 각 emp 객체를 접근하기 위해 empList로부터 Iterator<Employee> 객체를 구해서 활용함

	    System.out.println("사번                    이름                    직무                    급여                    수당	");
		System.out.println("---------------------------------------------------");
		
		Iterator<Employee> iterator =  empList.iterator();
	    while(iterator.hasNext()) {
	    	Employee emp = iterator.next();
	    	System.out.printf("%d %10s %10s %10.2f %10.2f \n", emp.getEmpNo(), emp.getEname(), emp.getJob(), emp.getSal(), emp.getComm());
	    }
	    
	    System.out.println();
		System.out.print("새 관리자 사번과 관리자 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
		
		compDao.replaceManagerOfDept(dept.getDeptNo(), managerNo, commission/* 부서번호, 관리자사번, 보직수당 */);

	    Employee manager = compDao.findEmpInfo(managerNo /* 관리자사번 */);   
	    
		// manager 객체의 필드 값 출력 ...
	    System.out.println("새 관리자 정보: ");
	    System.out.printf("%10s %10s %10.2f %10.2f %10s \n", manager.getEname(), manager.getJob(), manager.getSal(), manager.getComm(), manager.getDname());
	    
		scanner.close();
	}
}
