package dbcp1;

import java.sql.*;

public class DbcpTest1 {
	public static void main(String args[]){
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		String query = "SELECT * FROM EMP0000";

		try {
			ConnectionMgr1 cm = new ConnectionMgr1();		// DBCP connection manager ��ü ����
			conn = cm.getConnection(); 					// connection ȹ��
			pstmt = conn.prepareStatement(query);		
			rs = pstmt.executeQuery();
			
			System.out.println("No    Name");
			
			while (rs.next()) {					
				int no = rs.getInt("EMPNO");
				String name = rs.getString("ENAME");
				System.out.println(no + " " + name);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(conn != null) try { conn.close(); } catch(Exception e) {}
		}
	}
}