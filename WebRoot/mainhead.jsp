<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
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
  <jsp:useBean id="video" class="beans.Videos"></jsp:useBean>
  <jsp:setProperty name="video" property="name" value="<%=request.getParameter(\"username\") %>"/>
  <%
  String username=session.getAttribute("username").toString();
   %>
  <h3><a href="main.jsp?username=<%=username%>">回到主页    </a>   欢迎<a href="userinfo.jsp?username=<%=username%>"><%=session.getAttribute("username") %></a>,<a href="logout.jsp">退出登录</a></h3>
  <tr><td><a href="AllVideo.jsp?username=<%=username%>">所有视频</a></td>
  	  <td><a href="QueriedPage.jsp?username=<%=username %>&&keys=考研">考研</a></td>
  	  <td><a href="QueriedPage.jsp?username=<%=username %>&&keys=视频">视频</a></td>
  
  
  </tr>
  </body>
</html>
