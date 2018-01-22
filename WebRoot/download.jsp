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
	window.setInterval(getMessage, 5000);
	

	function getMessage() 
	{
		$.ajax({
        async :false,
        type : "GET",
        dataType : "json",
        url :"getmessage.action",
        success : function(data) {
        	//var json=eval("("+data+")");
        	if(data==null){
        		document.getElementById("message").innerHTML="";
        	}else{
     			document.getElementById("message").innerHTML=data.message.num;//
			}
		
	    }
	    });
	    }
	
	function go(FileName,FileSize,location1){
		location1=location1+"kuohao";
		location1=location1.replace(/\//g,"xiegang");
		location.href = "fileordir.action?FileName="+encodeURI(encodeURI(FileName))+"&FileSize="+encodeURI(encodeURI(FileSize))+"&location1="+encodeURI(encodeURI(location1));
	}
	
	function upload(){
	 var loc=document.getElementById('current_location').innerHTML;
	 loc=loc+"kuohao";
	 loc=loc.replace(/\//g,"xiegang");
	 var myfile=document.getElementById("myFile").value;
	 if(myfile!=""){
	 	document.getElementById("uploadprocess").innerHTML="<div class='progress'><div class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='min-width: 2em;width: 100%'>上传中...</div></div>";
     	document.uploadForm.action="upload.action?location1="+encodeURI(encodeURI(loc));
     	document.uploadForm.submit();
     }else{
     	document.getElementById("uploadBtn").innerHTML="请选择文件";
     }
   }
   
   

   
     function searchFile(){
	 var filename=searchForm.searchedFilename.value;
	 document.getElementById("searchprocess").innerHTML="<div class='progress'><div class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='min-width: 2em;width: 100%'>搜索中...</div></div>";
     document.searchForm.action="search.action?filename="+encodeURI(encodeURI(filename));
     document.searchForm.submit();
   }
	</script>
<script type="text/javascript">
		function load(){
		window.location.href = "getfile.action";i++;
		return;
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
    <table class="table table-hover table-bordered table-striped" id="mytable">
    <tr>
        <td>选择</td>
        <td>文件名</td>

       <td>文件大小</td>
       <td>修改时间</td>
    </tr>

    <c:forEach var="file" items="${filesList}" varStatus="status">   
         <tr>
         	<td>
         	<c:if test="${file.size=='null'}">
            	<input type="checkbox" data-toggle="toggle" data-on="已选中" data-off="文件夹" data-onstyle="success" data-offstyle="primary" data-size="small">
            </c:if>
            <c:if test="${file.size!='null'}">
            	<input type="checkbox" data-toggle="toggle" data-on="已选中" data-off="文件" data-onstyle="success" data-offstyle="info" data-size="small">
            </c:if>
            </td>
            <td style="cursor:pointer" onClick="javascript:go('${file.filename}','${file.size}','${file.location}')">${file.filename}</td>

           <td>${file.size}</td>
           <td>${file.time}</td>

        </tr>
    </c:forEach>

</table>
  </body>
</html>
