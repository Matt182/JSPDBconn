package persons;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DBtest viewer=null;
		try {
			viewer = new DBtest();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("problems with db test");
			e1.printStackTrace();
		}
		//OutputStream out = response.getOutputStream();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		ResultSet rs = viewer.getResultSet();
		if(rs == null){
			System.out.println("rs == nul");
		}
		try {
			pw.println("<html>");
			pw.println("<table>");
			while(rs.next()){
				pw.println("<tr>");
				pw.println("<td>" + rs.getObject(1) + "</td>");
				pw.println("<td>" + rs.getObject(2) + "</td>");
				pw.println("<td>" + rs.getObject(3) + "</td>");
				pw.println("<td>" + rs.getObject(4) + "</td>");
				pw.println("<td>" + rs.getObject(5) + "</td>");
				pw.println("<tr>");
			}
			pw.println("</table>");
			pw.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		if(login.equals("admin") && pass.equals("admin")){
			response.sendRedirect("/persons/DataBase.jsp");
		} else {
			response.sendRedirect("/persons/data.jsp");
		}
		
	}

}
