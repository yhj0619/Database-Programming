package dbp.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbp.beans.User;

import java.io.*;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
	
    public void init() {	// life-cycle init method
 	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		System.out.println(id + ", " + pw);
		
		LoginService dao = new LoginService();
		User userInfo = dao.login(id, pw);		
		request.setAttribute("userInfo", userInfo);			
		RequestDispatcher rd = request.getRequestDispatcher("login/loginResult.jsp");
		rd.forward(request, response);
	}
}
