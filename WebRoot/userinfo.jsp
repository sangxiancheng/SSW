<%@ page language="java" import="java.util.*" import="beans.Users" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>
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
  <jsp:useBean id="user" class="beans.Users"></jsp:useBean>
  <jsp:setProperty name="user" property="name"  value="<%=request.getParameter(\"username\") %>"/>
  <%
  ArrayList users = (ArrayList)user.getUserByName();
  for(Object o:users){
  	Map m = (HashMap)o;
  %>
   <form name="form" action="modification.action" method="post">
  <table class="table table-bordered">
  <caption><%=session.getAttribute("username") %>的个人信息</caption>
    <tr><th>用户名</th><td><input type="text" name="name" value="<%=m.get("Name") %>" readonly="true"></td></tr>	
    <tr><th>性别</th><td><input type = "text" name = "sex" value = "<%=m.get("Sex") %>"></td></tr>
	<tr><th>联系电话</th><td><input type="text" name="telephone" value="<%=m.get("Telephone") %>"></td></tr>
	<tr><th>专业</th><td><input type="text" name="major" value="<%=m.get("Major") %>"></td></tr>
	<tr><th>班级</th><td><input type="text" name="class1" value="<%=m.get("Class") %>"></td></tr>
	<tr><th>真实姓名</th><td><input type="text" name="truename" value="<%=m.get("TrueName") %>"></td></tr>
	<tr><th>Email</th><td><input type="text" name="email" value="<%=m.get("Email") %>"></td></tr>
	<tr><th colspan="2"><input type="submit" value="修改">
	<input type="reset" value="重置"></th></tr>
	</table>
	</form>
   <%
   }
    %>
  
  </body>
</html>
