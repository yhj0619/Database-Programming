package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class CompanyDao {
	private JDBCUtil jdbcUtil = null;	// JDBCUtil 참조 변수 선언
		
	public CompanyDao() {	// 생성자 
		jdbcUtil = new JDBCUtil();		// JDBCUtil 객체 생성 및 활용
		
/*	JDBCUtil 이용 시 불필요
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
*/
	}

/*	JDBCUtil 이용 시 불필요
	private Connection getConnection() {
		String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl";		
		String user = "dbpro";
		String passwd = "dbpro";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
*/

  public Department findDeptInfo(String deptName) {
  	// printDeptInfo()를 변형 
  	// Department 객체를 생성하고 검색 결과를 저장해서 return함
	 String query = "SELECT dname, deptno, manager, count(*) AS numOfEmps "
				+ "FROM DEPT0984 JOIN EMP0984 USING (deptno) "
				+ "WHERE dname = ? "
				+ "GROUP BY dname, deptno, manager";
	 
	Department dept = null;
	Object[] param = new Object[] { deptName };
	jdbcUtil.setSqlAndParameters(query, param);	// JDBCUtil 에 query 및 파라미터 설정
	
	try { 
		ResultSet rs = jdbcUtil.executeQuery();	

		while(rs.next()) {
			dept = new Department();
			dept.setDeptNo(rs.getInt("deptno"));  
			dept.setDname(rs.getString("dname"));
			dept.setMgrNo(rs.getInt("manager"));
			dept.setNumOfEmps(rs.getInt("numOfEmps"));  
		} 
		return dept;
	}catch (SQLException ex) {
		ex.printStackTrace();
	} finally {	
		jdbcUtil.close();
	}	
	return null;
  } 

  public List<Employee> findEmployeesInDept(int deptNo) {
  	// printEmployeesInDept()를 변형
  	// ArrayList<Employee> 객체를 생성하고, 검색된 각 사원 정보에 대해 Employee 객체를 생성 및 저장하고, 그 객체를 ArrayList에 추가함
	  String query = "SELECT empno, ename, job, sal, comm "
					+ "FROM DEPT0984 JOIN EMP0984 USING (deptno) "
					+ "WHERE deptNo= ?";
		
	  	Object[] param = new Object[] { deptNo };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();						
			List<Employee> list = new ArrayList<Employee>();
			while (rs.next()) {	
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setSal(rs.getFloat("sal"));
				emp.setComm(rs.getFloat("comm"));
				
				list.add(emp);
			}
			return list;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}	
	  return null;
  }

  
  public void replaceManagerOfDept(int deptNo, int managerNo, double commission) {
      // 기존과 동일
	  	String update1 = "UPDATE DEPT0984 SET manager = ? WHERE deptNo = ? ";
		String update2 = "UPDATE EMP0984 SET comm = NVL(comm, 0) + ? WHERE empno = ? ";
		
		try {			
			Object[] param = new Object[] { managerNo, deptNo };
			jdbcUtil.setSql(update1);
			jdbcUtil.setParameters(param);
			
			jdbcUtil.executeUpdate();			
			jdbcUtil.close();
			
			Object[] param2 = new Object[] { commission, managerNo };
			jdbcUtil.setSql(update2);
			jdbcUtil.setParameters(param2);
			
			jdbcUtil.executeUpdate();	
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}	
  }
  
  public Employee findEmpInfo(int empNo) {
      // printEmpInfo()를 변형
  	// Employee 객체를 생성하고 검색 결과를 저장해서 return함
	  String query = "SELECT ename, job, sal, comm, dname "
				+ "FROM DEPT0984 JOIN EMP0984 USING (deptno) "
				+ "WHERE empNo= ? ";
		
	  	Object[] param = new Object[] { empNo };
	  	jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			Employee emp = null;
			while (rs.next()) {	
				emp = new Employee();
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDname(rs.getString("dname"));
			}
			return emp;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}
      return null;
  }
} 

