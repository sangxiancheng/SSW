<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>DeleteVideoPage</title>
    
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
  <jsp:setProperty name="video" property="videoid" value="<%=request.getParameter(\"VideoID\") %>"/>
  <jsp:setProperty name="video" property="videoname" value="<%=request.getParameter(\"VideoName\") %>"/>
  <jsp:setProperty name="video" property="name" value="<%=username %>"/>
   <%
   boolean result = video.delVideo();
   if(result==true){
	out.println("删除视频成功！");
	
	}
	else{
	out.println("删除视频失败！");
	}
	out.println("5秒后自动跳转至主页面！");
	response.setHeader("Refresh","5;url=main.jsp?username="+username);  
   %>
  </body>
</html>
