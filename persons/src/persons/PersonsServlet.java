package persons;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PersonsServlet")
public class PersonsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PersonsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// проверяем логин и пароль
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		if(login.equals("admin") && pass.equals("admin")){
			response.sendRedirect("/persons/DataBase.jsp");
		} else {
			response.sendRedirect("/persons/data.jsp");
		}
		
	}

}
