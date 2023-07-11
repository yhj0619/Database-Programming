package dbp.helloWorld;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.text.*;
import java.io.*;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	private String time;
  	private String message;
  	private SimpleDateFormat format;
  	
	public HelloWorldServlet() {
        super();
    }
	
    public void init() {			// life-cycle init method
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// time = format.format(new Date());
 	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		time = format.format(new Date());
		message = "Hello " + request.getParameter("name");
		int start_num = 1, end_num = 5;
		
	    response.setContentType("text/html; charset=EUC_KR");
		
	    PrintWriter out = response.getWriter();
		out.print("<HTML><HEAD><TITLE>"); out.print(message); out.println("</TITLE></HEAD></HTML>");
	    out.println("<BODY>");
	    out.println("<H1>HelloWorldSevlet</H1>");
	    out.println("<B>" + message + "</B><BR><BR>");
	 	for(int i = start_num; i <= end_num; i++) {
			out.print(i); out.print(" : "); out.print(time); out.println("<br/>"); 
	    }   	
		out.println("</BODY>"); out.println("</HTML>");
	}
}
