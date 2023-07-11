<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.sql.*, javax.sql.DataSource, javax.naming.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DBCP Test</title>
</head>
<body>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = "SELECT * FROM DEPT";
	Context initContext = null;
	Context envContext = null;
	DataSource ds = null;
	
	try {
		initContext = new InitialContext();
		envContext = (Context)initContext.lookup("java:/comp/env");
		ds = (DataSource)envContext.lookup("jdbc/TestDB");
	} catch (Exception ex) { ex.printStackTrace(); }

	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
%>
<br>
DeptNo&nbsp;&nbsp;&nbsp;Name<br>
-----------------------------<br>
<%
		while (rs.next()) {
			int no = rs.getInt("DEPTNO");
			String name = rs.getString("DNAME");
%>
<%=no%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=name%><br>
<%			
		}
	} catch (SQLException ex) { 
		ex.printStackTrace(); 
	} finally {
		if(rs != null) try { rs.close(); } catch(Exception e) {}
		if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
		if(conn != null) try { conn.close(); } catch(Exception e) {}
	}
%>
<br>
<a href=dbcpTest>DbcpTestServlet 요청 (/dbcpTest)</a>
</body>
</html>