package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		catch(ClassNotFoundException e){
			System.out.println("1"); 
			e.printStackTrace();
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
		System.out.println("字符串的长度为："+params.length);
		
		System.out.println(params[0]);
		if(params!=null)
		{
		for(int j=0;j<params.length;j++)
		{
			System.out.println(params[j]);
		}
		for(int i=0;i<params.length;i++)
			try{
				
				pstmt.setString(i+1, params[i]);
			}
			catch(SQLException e){
				System.out.println("3");
				e.printStackTrace();
			}
		}
	}

	public List getList(String sql,String[] params){
		List list = new ArrayList();
		try{
			this.setParams(sql, params);
			ResultSet rs=pstmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			while(rs.next()){
				Map m = new HashMap();
				int count=rsmd.getColumnCount();
				String[] name=new String[count];
				for(int i = 0;i<count;i++){
					name[i]=rsmd.getColumnName(i+1);
					//String colName = rsmd.getCatalogName(i);
					m.put(name[i], rs.getString(name[i]));
				}
				list.add(m);
			}
		}catch(SQLException e){
			System.out.println("未查找到行！");
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	public String getPassword(String sql,String[] params){
		String recPw = null;
		try{
			setParams(sql,params);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			recPw = rs.getString("Password");
			}
		}
		catch(Exception e){
			System.out.println("3");
			e.printStackTrace();
		}
		return recPw;
	}
	public Map getMap(String sql,String[] params){
		List list = getList(sql,params);
		if(list.isEmpty())
			return null;
		else
			return (Map)list.get(0);
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