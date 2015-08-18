package persons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBtest {
	public static Connection conn;
	private static Statement stmt = null;        
    private static ResultSet rs = null;
    private static DBtest instance = null;

    public ResultSet getResultSet(){
    	
    	return rs;
    }

    public static void insert(String id,String firstName, String secondName, String address, String dateOfBirth){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytables","root","root");
			stmt = conn.createStatement();
			stmt.execute("insert into persons values (null,'" +firstName + "','" +secondName+ "','" +address + "','" +dateOfBirth + "')");
			
			rs = stmt.executeQuery("select * from persons");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database from isert method");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load jdbc driver");
			e.printStackTrace();
		}
    }
    
	public DBtest() throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytables","root","root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from persons");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load jdbc driver");
			e.printStackTrace();
		}
		/**
		while(rs.next()){
			System.out.print(rs.getInt("id") + " ");
			System.out.print(rs.getString("firstName") + " ");
			System.out.print(rs.getString("secondName") + " ");
			System.out.print(rs.getString("adress") + " ");
			System.out.println(rs.getDate("dateOfBirth"));
		}
		**/
		/**
		PreparedStatement ps = conn.prepareStatement("insert into persons values(null, ?, ?, ?, ?)");
		ps.setString(1, "peter");
		ps.setString(2, "johnson");
		ps.setString(3, "Liberty street");
		
		String dob = "1997-01-28";
		Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
		} catch (ParseException e) {
			System.out.println("error while parisng date");
		}
		// because PreparedStatement#setDate(..) expects a java.sql.Date argument
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		ps.setDate(4, sqlDate);
		ps.execute();
		**/
		

	}

}
