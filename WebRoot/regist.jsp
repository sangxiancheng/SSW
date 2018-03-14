<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="Users" class="beans.Users" />
<jsp:setProperty name="Users" property="*" />
<%
	int result = Users.addUser();
	String name=request.getParameter("name");
	
	if(result == 1){
		request.setCharacterEncoding("UTF-8");
		session.setAttribute("username", name);
  		out.println("<h3>注册成功5秒后自动跳转至主页面...<a href=\"main.jsp\">主页面</a>!</h3>");
  		response.setHeader("Refresh","5;url=main.jsp");
	}
	else{
	out.println("<h3>注册失败，该用户名已存在，3秒后自动跳转至注册页面...<a href=\"register.html\">注册页面</a>!</h3>");
  		response.setHeader("Refresh","3;url=register.html");
	}
%>