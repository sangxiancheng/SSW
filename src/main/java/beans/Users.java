package beans;
import java.util.List;
import java.util.Map;
import beans.DBUtil;
public class Users {
	private String name;// 用户姓名
	private String sex;//性别
	private String password;//密码
	private String telephone;//联系电话
	private String major;//专业
	private String class1;//班级
	private String truename;//真实姓名
	private String email;//电子邮箱
	private DBUtil db;
	public Users(){
		db = new DBUtil();
	}
	public String getName(){
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
	//添加用户信息
	public int addUser(){
		int result = 0;
		String sql = "insert into usertable(Name,Sex,Password,Telephone) values(?,?,?,?)";
		String[] params = {name,sex,password,telephone};
		result = db.update(sql, params);
		return result;
	}
	//查看所有用户信息
	public List getAllUsers(){
		List users = null;
		String sql = "select * from ssw.usertable";
		users = db.getList(sql, null);
		return users;
	}
	//通过用户Name查询用户信息
	public List getUserByName(){
		List user = null;
		String sql = "select * from usertable where Name=?";
		String[] params={name};
		user = db.getList(sql, params);
		return user;
	}
	//通过用户Name修改用户信息
	public int updateUser(){
		int result = 0;
		String sql = "update usertable set Sex=?,Major=?,Class=?,TrueName=?,Email=? where Name=?";
		String[] params = {sex,major,class1,truename,email,name};
		result = db.update(sql, params);
		return result;
	}
	//删除用户信息
	public int delUser(){
		int result = 0;
		String sql = "delete from usertable where Name=?";
		String[] params = {name};
		result = db.update(sql, params);
		return result;
	}
	public String getPw(){
		String result = null;
		String sql = "select Password from usertable where Name=?";
		String[] params = {name};
		result = db.getPassword(sql, params);
		return result;
	}
}
