<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>视频播放</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.center
		{
			border-style: solid solid solid solid;
			position: absolute;
  			left: 30%;
  			top: 50px;
		}
	</style>
  </head>
  
  <body>
  <% 
  String VideoName=request.getParameter("VideoName");
  String VideoLocation=request.getParameter("VideoLocation");
  //out.print(VideoName);
  //out.print(VideoLocation);
  %>
   <video width="480" height="360" controls class="center">
	<source src="http://localhost:8080/SSW/stream?fpath=/ssw/sang/poop.ogg" type="video/mp4">
	</video> 
  </body>
</html>
