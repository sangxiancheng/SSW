<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>文件上传</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  

  </head>  
    
  <body>  
   <center>  
    <h1>选择文件上传</h1>  
      <form action="upload.action" method="post" enctype="multipart/form-data">  
        <table>  
            <tr>  
                <td>用户名:</td>  
                <td><input type="text" name="username" ></td>  
            </tr>  
            <tr>  
                <td>上传文件:</td>  
                <td><input type="file" name="myFile"></td>  
            </tr>  
            <tr>  
                <td><input type="submit" value="上传"></td>  
                <td><input type="reset"></td>  
            </tr>  
        </table>  
      </form> 
      <form action="download.action" method="post" enctype="multipart/form-data">  
        <table>  
            <tr>  
                <td>文件名:</td>  
                <td><input type="text" name="FileName" ></td>  
            </tr>  
            <tr>  
                <td><input type="submit" value="下载"></td>   
            </tr>  
        </table>  
      </form>  
  </center>  
  </body>  
</html>  