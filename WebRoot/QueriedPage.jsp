<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=request.getParameter("keys") %>类视频</title>
    
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
  <jsp:setProperty name="video" property="videokeys" value="<%=request.getParameter(\"keys\") %>"/>
   <table align="center" width="50%" border="1">
  <caption><%=request.getParameter("keys") %>类视频</caption>
  <tr><th>序号</th><th>视频名称</th><th>视频关键字</th><th>视频大小</th><th>更新日期</th><th>上传者</th><th>详情</th><th>在线播放</th><th>下载</th></tr>
   <%
  ArrayList videos = (ArrayList)video.getVideosByVideoKeys();
  int i = 1;
  for(Object o:videos){
  	Map m = (HashMap)o;
   %>
   
   <tr>
   <form action="download.action" method="post" enctype="multipart/form-data">  
   <td><%=i++ %></td>
   <td><%=m.get("VideoName") %></td>
   <td><%=m.get("VideoKeys") %></td>
   <td><%=m.get("VideoSize") %>KB</td>
   <td><%=m.get("date(VideoDate)") %></td>
   <td><%=m.get("Name") %></td>
   <td><a href="videodetail.jsp?VideoID=<%=m.get("VideoID")%>">详情</a></td>
   <td><a href="MyStream.jsp?VideoLocation=<%=m.get("VideoLocation") %>&VideoName=<%=m.get("VideoName")%>">播放</a></td>
   	<input type="hidden" name="VideoName" value="<%=m.get("VideoName") %>">
	<input type="hidden" name="name" value="<%=m.get("Name")%>">
   <td><input type="submit" value="下载"></td>
   </form> 
   </tr>
   <%
   }
   %>
   </table>
  </body>
</html>
