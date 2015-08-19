package persons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBtest {
	public static Connection conn;
	private static Statement stmt = null;        
    private static ResultSet rs = null;
    private static ArrayList<Integer> ids = new ArrayList<>();

    public ResultSet getResultSet(){
    	
    	return rs;
    }

    public static void insert(String id,String firstName, String secondName, String address, String dateOfBirth){
    	try {
    		// получаем список имеющихся id
    		stmt = conn.createStatement();
			ResultSet identics = stmt.executeQuery("select id from persons");
			ids.clear();
			while (identics.next()){
				ids.add(identics.getRow());
			}
			
			//проверяем имеется ли добавляемое id в списке, если да то зменяем эту строку, если нет то добавляем новую
    		if(!(id.equals("")) && ids.contains(Integer.parseInt(id))){
    			stmt.execute("update persons set firstName ='" +firstName + "', secondName ='" +secondName+ "', adress ='" +address + "',dateOfBirth ='" +dateOfBirth + "' where id ="+id);
    		} else {
    			stmt.execute("insert into persons values (null,'" +firstName + "','" +secondName+ "','" +address + "','" +dateOfBirth + "')");
    		}
    		
    		//обновляем таблицу
			rs = stmt.executeQuery("select * from persons");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database from isert method");
			e.printStackTrace();
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
	}
}
