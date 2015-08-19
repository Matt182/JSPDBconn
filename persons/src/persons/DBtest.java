package persons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBtest {
	public static Connection conn;
	private static Statement stmt = null;        
    private static ResultSet rs = null;
    private static DBtest instance = null;
    private static ArrayList<Integer> ids = new ArrayList<>();

    public ResultSet getResultSet(){
    	
    	return rs;
    }

    public static void insert(String id,String firstName, String secondName, String address, String dateOfBirth){
    	try {
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytables","root","root");
    		stmt = conn.createStatement();
			ResultSet identics = stmt.executeQuery("select id from persons");
			ids.clear();
			while (identics.next()){
				ids.add(identics.getRow());
			}

    		if(!(id.equals("")) && ids.contains(Integer.parseInt(id))){
    			stmt.execute("update persons set firstName ='" +firstName + "', secondName ='" +secondName+ "', adress ='" +address + "',dateOfBirth ='" +dateOfBirth + "' where id ="+id);
    		} else {
    			stmt.execute("insert into persons values (null,'" +firstName + "','" +secondName+ "','" +address + "','" +dateOfBirth + "')");
    		}
			//stmt = conn.createStatement();
			//stmt.execute("insert into persons values (" + id + ",'" +firstName + "','" +secondName+ "','" +address + "','" +dateOfBirth + "')");
			
			rs = stmt.executeQuery("select * from persons");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database from isert method");
			e.printStackTrace();
		//} catch (ClassNotFoundException e) {
			//System.out.println("Unable to load jdbc driver");
			//e.printStackTrace();
		}
    }
    
    
    public static void delete(String id){
    	
    	try {
    		stmt = conn.createStatement();
			stmt.execute("delete from persons where id =" + id);
			rs = stmt.executeQuery("select * from persons");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	/**
	 * @throws SQLException
	 */
	public DBtest() throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mytables"; 
			conn = DriverManager.getConnection(url,"root","root");
			stmt = conn.createStatement();
			stmt.execute("set names utf8");
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
