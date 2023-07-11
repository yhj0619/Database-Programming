package dbcp3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class EmpDAO {
	private DataSource ds;
	
	public EmpDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/TestDB");
			// refer to resource settings in WebContent/META-INF/Context.xml
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	public List<EmpDTO> findEmpList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT empno, ename FROM EMP0000";
		List<EmpDTO> empList = new ArrayList<EmpDTO>();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpDTO emp = new EmpDTO();
				emp.setEmpNo(rs.getString("empno"));
				emp.seteName(rs.getString("ename"));
				empList.add(emp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
			if (conn != null) { 
				try { conn.close(); } 
				catch (SQLException ex) { ex.printStackTrace(); }
			}
		}
		return empList;
	}
}
