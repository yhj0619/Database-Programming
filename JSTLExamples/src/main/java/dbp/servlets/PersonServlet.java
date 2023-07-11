package dbp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbp.beans.Dog;
import dbp.beans.Person;

import java.io.*;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/person") //url임 클릭전에 뜨는  url임!!
public class PersonServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	public PersonServlet() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dog myDog = new Dog();
		myDog.setName("spike");
		Person p = new Person();
		p.setName("Evan");
		p.setDog(myDog);
		
		request.setAttribute("person", p);
		request.getRequestDispatcher("/WEB-INF/results/person.jsp").forward(request, response);
    }
}
