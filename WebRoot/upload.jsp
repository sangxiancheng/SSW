<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>视频上传页面</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
  
  </head>  
    
  <body> 
  <jsp:setProperty name="video" property="name" value="<%=request.getParameter(\"username\") %>"/> 
   <center>  
    <h3>选择文件上传</h3>
      <form action="upload.action" method="post" enctype="multipart/form-data">  
        <table>  
            <tr>  
                <th>上传文件:</th><td><input type="file" name="myFile" multiple="multiple" accept="application/ogg, audio/ogg"></td> 
                <input type="hidden" name="username" value="<%=request.getParameter("username") %>"/>     
            </tr>
            <tr>
            <th>视频关键词:</th><td><input type="text" name="keys"></td>
            </tr>
            <tr>
            <th>视频描述:</th><td><textarea name="comments" rows="5" cols="30"></textarea></td></tr>
            <tr>  
                <td><input type="submit" value="上传"></td>  
                <td><input type="reset"></td>  
            </tr>  
        </table>  
      </form> 
  </center>  
  </body>  
</html>  