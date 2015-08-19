
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function update(){
			document.getElementById("func").value = "edit";
			
		}
	</script>
	<script type="text/javascript">
		function del(){
			document.getElementById("func").value = "delete";
		}
	</script>
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
				<table class="dataBase">
				<tr class="dataBaseR">
		<%
			for (int i  = 1;i <= rs.getMetaData().getColumnCount(); i++){
				%>

					<th class="dataBaseH"><%=rs.getMetaData().getColumnName(i) %></th>

				<%
			}%>
				</tr >
			<%
				while(rs.next()){
					%>
					<tr class="dataBaseR">
					<td class="dataBaseD"> <%= rs.getObject(1) %> </td>
					<td class="dataBaseD"> <%= rs.getObject(2) %> </td>
					<td class="dataBaseD"> <%= rs.getObject(3) %> </td>
					<td class="dataBaseD"> <%= rs.getObject(4) %> </td>
					<td class="dataBaseD"> <%= rs.getObject(5) %> </td>
					</tr>
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
			<p>Для редактирования введите такой же Id редактируемого, для удаления достаточно ввести только Id</p>
			<input type=hidden id =func name = func > <!-- невидимое поле которое принимает парамтр delete или edit
															в зависимости от того какая кнопка нажата  -->
			<table>
				<tr>
					<td>Enter ID: </td><td><input type=text name = id></td>			
					<td>Enter first name: </td><td><input type=text name = firstName></td>
				</tr>
				<tr>
					<td>Enter second name: </td><td><input type=text name = secondName></td>
					<td>Enter address: </td><td><input type=text name = address></td>
				</tr>
				<tr>
					<td>Enter date of birth (YYYY-MM-DD): </td><td><input type=text name = dateOfBirth></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td><td><input type=submit value='Add' onclick=update(); id='button'></td>
					<td><input type=submit value='Delete' onclick=del(); id='button'></td><td></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>