package beans;
import java.sql.*;
public class Users {
	private String name;// 用户姓名
	private String sex;//性别
	private String password;//密码
	private String telephone;//联系电话
	private int returnNum;//返回数
	public String  getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getSex(){
		return sex;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getTelephone(){
		return telephone;
	}
	public void setTelephone(String telephone){
		this.telephone=telephone;
	}
	public int addStudent(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:" +
					"//118.25.16.118:3306/ssw","ssw","smussw222");			
			String sql = "insert into usertable(Name,Sex,Password,Telephone) values(?,?,?,?)";
			String[] params = {name,sex,password,telephone};
			PreparedStatement pstmt=con.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
				try{
					pstmt.setString(i+1, params[i]);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			returnNum=pstmt.executeUpdate();
		}
		catch(Exception e){ 
			e.printStackTrace();
		}
		return returnNum;
	}
}
