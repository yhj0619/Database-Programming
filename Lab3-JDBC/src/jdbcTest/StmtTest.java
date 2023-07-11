package jdbcTest;

import java.sql.*;									// 1. JDBC 愿��젴 package import

public class StmtTest {
	public static void main(String args[]) 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl";		
		String user = "scott";
		String passwd = "TIGER";
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// 2. JDBC Driver 濡쒕뵫 諛� �벑濡�
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		String query =  "SELECT empno, ename, dname "
				  + "FROM emp JOIN dept ON emp.deptno = dept.deptno";						 
	
		try {			
			conn = DriverManager.getConnection(url, user, passwd);	// 3. DBMS���쓽 �뿰寃� �쉷�뱷
			stmt = conn.createStatement();				// 4. SQL臾몄쓣 �쐞�븳 Statement 媛앹껜 �깮�꽦 
			rs = stmt.executeQuery(query);				// 5. Statement 媛앹껜瑜� �궗�슜�븯�뿬 SQL臾� �떎�뻾
			
			System.out.println("No    Name    Dept");
			while (rs.next()) {							// 6. DBMS �쓳�떟 �궗�슜
				int no = rs.getInt("empno");
				String name = rs.getString("ename");
				String dname = rs.getString("dname");				
				System.out.println(no + "  " + name + "  " + dname);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {										// 7. �옄�썝 諛섎궔
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (stmt != null) 
				try { 
					stmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}	
	}	
}