package persons;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IH
 */
@WebServlet("/IH")
public class IH extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static String sqlQuery;
	public static void doJob(String id, String firstName, String secondName, String address, String dateOfBirth){
		sqlQuery = "insert into persons values("+ id + ", " +firstName + ", " +secondName + ", " +address + ", " +dateOfBirth +")";	
	}
	public static String getQuery(){
		return sqlQuery;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBtest.insert(request.getParameter("id"),request.getParameter("firstName"),request.getParameter("secondName"),
				request.getParameter("address"),request.getParameter("dateOfBirth"));
		try {
			response.sendRedirect("/persons/DataBase.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
