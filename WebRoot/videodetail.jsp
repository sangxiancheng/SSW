<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>视频详情页面</title>
    
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
  <jsp:useBean id="video" class="beans.Videos"/>
  <jsp:setProperty name="video" property="videoid" value="<%=request.getParameter(\"VideoID\") %>"/>
   <%=request.getParameter("VideoID") %>
   <%
   //String id=request.getParameter("VideoID"); 
   HashMap videoinfo = (HashMap)video.getVideoByVideoID();
   if(videoinfo!=null){
   %>
   <form>
 
   <table align="center" width="50%" border="1">
   <tr><th width="30%">视频ID:</th><td><%=videoinfo.get("VideoID") %></td></tr>
   <tr><th>视频名称:</th><td><%=videoinfo.get("VideoName") %></td></tr>
   <tr><th>视频关键字:</th><td><%=videoinfo.get("VideoKeys") %></td></th>
   <tr><th>视频描述:</th><td><%=videoinfo.get("VideoComments") %></td></th>
   <tr><th>视频大小:</th><td><%=videoinfo.get("VideoSize") %></td></tr>
   <tr><th>视频更新日期:</th><td><%=videoinfo.get("date(VideoDate)") %></td></tr>
   <tr><th>上传者:</th><td><%=videoinfo.get("Name") %></td></tr>
   <%
   }
   %>
   </table>
   </form>
  </body>
</html>
