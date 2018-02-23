<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'download.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function go(FileName,FileSize,location1){
		location1=location1+"kuohao";
		location1=location1.replace(/\//g,"xiegang");
		location.href = "fileordir.action?FileName="+encodeURI(encodeURI(FileName))+"&FileSize="+encodeURI(encodeURI(FileSize))+"&location1="+encodeURI(encodeURI(location1));
	}
	</script>
  </head>
  
  <body>
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
      <form action="MyStream.jsp">
      <table>
      <tr>
      <td>请输入需要播放的文件名：</td>
      <td><input type="text" name="VideoName"></td>
      </tr>
      <tr>
      <td><input type="submit" value="播放"></td>
      </tr>
      </table>
      </form> 
      <form action="getall.action" method="post" enctype="multipart/form-data">
    <table>
    <a href="getall.action">全部文件</a>
    <tr>
        <td>选择</td>
        <td>文件名</td>
      	<td>文件大小</td>
        <td>修改时间</td>
    </tr>
    <c:forEach var="file" items="${filesList}" varStatus="status">   
    </c:forEach>
</table>
</form>
  </body>
</html>
