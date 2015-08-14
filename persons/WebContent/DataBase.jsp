<%@page import="persons.InsertHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<%@ page import="java.util.*" %>
		<%@ page import="javax.sql.*" %>
		<%@ page import="persons.DBtest" %>
		<%@ page import="java.sql.SQLException" %>
		<%@ page import="java.io.PrintWriter" %>
		<%@ page import="java.sql.ResultSet" %>
		<%
		
			persons.DBtest viewer=null;
			try {
				viewer = new DBtest();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("problems with db test");
				e1.printStackTrace();
			}
			// OutputStream out = response.getOutputStream();
			// response.setContentType("text/html; charset=UTF-8");
			// PrintWriter pw = response.getWriter();
			
			ResultSet rs = viewer.getResultSet();
			if(rs == null){
				System.out.println("rs == nul");
			}
			try {
		%>
				<table>
		<%
				while(rs.next()){
					%>
					<tr>
					<td> <%= rs.getObject(1) %> </td>
					<td> <%= rs.getObject(2) %> </td>
					<td> <%= rs.getObject(3) %> </td>
					<td> <%= rs.getObject(4) %> </td>
					<td> <%= rs.getObject(5) %> </td>
					<tr>
					<%
				}
				rs.close();%>
								
				</table>
		<%
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
		
		%>
	</div>
	<div>
		<form action="IH" method=post>
			<p>Для редактирования введите такой же Id редактируемого, для удаления введите только Id</p>
			Enter ID ( uniq number): <input type=text name = id>			
			Enter first name: <input type=text name = firstName><br/>
			Enter second name: <input type=text name = secondName>
			Enter address: <input type=text name = address><br/>
			Enter date of birth (YYYY-MM-DD): <input type=text name = dateOfBirth>
			<input type=submit value='Add'>
		</form>
	</div>
	Administration view will be here
	<div></div>
</body>
</html>