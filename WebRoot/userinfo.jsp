<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=session.getAttribute("username") %>的详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  	String username=session.getAttribute("username").toString();
   %>
  <h3>欢迎<a href="userinfo.jsp"><%=session.getAttribute("username") %></a><a href="logout.jsp">退出登录</a></h3>
  <jsp:useBean id="user" class="beans.Users"></jsp:useBean>
  <jsp:setProperty name="user" property="name"  value="<%=request.getParameter(\"username\") %>"/>
  <table align="center" width="50%" border="1">
  <caption><%=session.getAttribute("username") %>的个人信息</caption>
  <%=request.getParameter("username") %>
  <tr><th>用户名</th><th>密码</th><th>性别</th><th>专业</th><th>班级</th><th>联系电话</th><th>真实姓名</th><th>电子邮箱</th></tr>
  <%
  ArrayList users = (ArrayList)user.getUserByName();
  for(Object o:users){
  	Map m = (HashMap)o;
   %>
   <tr>
   <td><%=m.get("Name") %></td>
   <td><%=m.get("Password") %></td>
   <td><%=m.get("Sex") %></td>
   <td><%=m.get("Major") %></td>
   <td><%=m.get("Class") %></td>
   <td><%=m.get("Telephone") %></td>
   <td><%=m.get("TrueName") %></td>
   <td><%=m.get("Email") %></td>
   </tr>
   <%
   }
    %>
  </table>
  </body>
</html>
