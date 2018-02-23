package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBUtil {
	private String driver;
	private String url;
	private String username;
	private String password;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public void setDriver(String driver){
		this.driver=driver;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public DBUtil(){
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://118.25.16.118:3306/ssw";
		username="ssw";
		password="smussw222";
	}
	private Connection getConnection(){
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, username, password);
		}
		catch(Exception e){
			System.out.println("1"); 
			e.printStackTrace();
		}
		return con;
	}
	private PreparedStatement getPrepareStatement(String sql){
		try{
			pstmt=getConnection().prepareStatement(sql);
		}
		catch(Exception e){
			System.out.println("2");
			e.printStackTrace();
		}
		return pstmt;
	}
	private void setParams(String sql,String[] params){
		pstmt=this.getPrepareStatement(sql);
		for(int i=0;i<params.length;i++) {
					
			try{
				pstmt.setString(i+1, params[i]);
			}
			catch(Exception e){
				System.out.println("3");
				e.printStackTrace();
			}
		}
	}
	public int update(String sql,String[] params) {
		int recNo=0;
		try {
			setParams(sql,params);
			recNo=pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("4");
			e.printStackTrace();
		}
		finally {
			close();
		}
		return recNo;
	}
	private void close() {
		try {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
		}
		catch(SQLException e) {
			System.out.println("5");
			
		}
	}
}