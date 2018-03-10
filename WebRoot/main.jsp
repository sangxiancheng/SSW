<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教学视频存储和共享平台</title>
    
    <meta http-equiv="charset" content="utf-8">
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
  String name=request.getParameter("username");
  out.println("<h3>欢迎"+name+",登录成功！</h3>");
   %>
  <jsp:useBean id="video" class="beans.Videos"></jsp:useBean>
  <table align="center" width="50%" border="1">
  <caption>所有视频信息</caption>
  <tr><th>视频ID</th><th>视频名称</th><th>视频关键字</th><th>视频大小</th><th>更新日期</th><th>上传者</th><th>详情</th><th>在线播放</th><th>下载</th></tr>
   <%
  ArrayList videos = (ArrayList)video.getAllVideos();
  for(Object o:videos){
  	Map m = (HashMap)o;
   %>
   <tr>
   <td><%=m.get("VideoID") %></td>
   <td><%=m.get("VideoName") %></td>
   <td><%=m.get("VideoKeys") %></td>
   <td><%=m.get("VideoSize") %></td>
   <td><%=m.get("date(VideoDate)") %></td>
   <td><%=m.get("Name") %></td>
   <td><a href="videodetail.jsp?VideoID=<%=m.get("VideoID")%>">详情</a></td>
   <td><a href="MyStream.jsp?VideoName=<%=m.get("VideoName")%>">播放</a></td>
   <form action="download.action" method="post" enctype="multipart/form-data">   
		<input type="hidden" name="VideoName" value="movie.ogg">
    	<td><input type="submit" value="下载"></td>
   </form> 
   </tr>
   <%
   }
   %>
   <tr><a href="upload.jsp?username=<%=request.getParameter("username") %>">上传视频</a></tr>
   </table>
  </body>
</html>
