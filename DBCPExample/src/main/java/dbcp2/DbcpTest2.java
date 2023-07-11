package dbcp2;

import java.sql.*;


public class DbcpTest2{
	public static void main(String args[]){
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		String query = "SELECT * FROM DEPT0000";

		try {
			ConnectionMgr2 cm = new ConnectionMgr2();	// DBCP connection manager ��ü ����
			conn = cm.getConnection(); 					// connection ȹ��
			pstmt = conn.prepareStatement(query);		
			rs = pstmt.executeQuery();
			
			System.out.println("DeptNo   Name");
			
			while (rs.next()) {					
				int no = rs.getInt("DEPTNO");
				String name = rs.getString("DNAME");
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