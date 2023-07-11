<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, java.text.*" %>	<!-- 지시자(directive) -->
<%! 
    private String message; 		 	// 선언(declaration)
    private String time;
    private SimpleDateFormat format; 
    
    public void init() {			// life-cycle init method
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// time = format.format(new Date());
	}
%>		
<%	// scriptlet
	time = format.format(new Date());
	message = "Hello " + request.getParameter("name"); 
	int start_num = 1, end_num = 5;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><%=message%></title>		<!-- 표현식(expression) -->
</head>
<body>
<h1>helloWorld.jsp</h1>
<b><%=message%></b><br><br>
<% for(int i = start_num; i <= end_num; i++) { %>	
       <%= i %> : <%= time %><br/>
<% } %>
</body>
</html>