package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	public static String url="jdbc:mysql://localhost:3306/card?useUnicode=true&characterEncoding=utf-8";
	public static String user="root";
	public static String password="sa";
	private JdbcUtils(){
		
	}
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection()throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	public static void free1(Statement st,Connection conn){
		try{
			if(st!=null)
				st.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void free2(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs!=null)
				rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(st!=null)
					st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String arg[])
	{
		
	}
}
