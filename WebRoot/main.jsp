<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>教学视频存储和共享平台</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="charset" content="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-table.css">
    
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <script src="js/MainTable.js"></script>
    
  </head>
  <body>
<<<<<<< HEAD
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">  
				<tr><a class="btn btn-primary pull-right" href="upload.jsp?username=<%=session.getAttribute("username") %>">上传视频</a></tr>
			</div>
			<div class="col-md-12 column">
			  	<div class="row clearfix">
					<div class="col-md-12 column">
					  <table id="MainTable" style="background:white">
					   <thead style="background:white">
					  	<tr>
					        <th>序号</th>
					        <th>视频名称</th>
					        <th>视频关键字</th>
					        <th>视频大小</th>
					        <th>更新日期</th>
					        <th>上传者</th>
					        <th>详情</th>
					        <th>播放</th>
					        <th>下载</th>
					        <th>删除</th>
					    </tr>
					    </thead>
					  <%
					 ArrayList videos = (ArrayList)video.getVideosByName();
					 int i=1;
					 for(Object o:videos){
					 	Map m = (HashMap)o;
					  %>
					  
					  <tr class="text-center">
					  <form action="download.action" method="post" enctype="multipart/form-data">  
					   <td><%=i++%></td>
					   <td><%=m.get("VideoName") %></td>
					   <td><%=m.get("VideoKeys") %></td>
					   <td><%=m.get("VideoSize") %>KB</td>
					   <td><%=m.get("date(VideoDate)") %></td>
					   <td><%=m.get("Name") %></td>
					   <td><a class="btn btn-info" href="videodetail.jsp?VideoID=<%=m.get("VideoID")%>">详情</a></td>
					   <td><a class="btn btn-primary" href="MyStream.jsp?VideoLocation=<%=m.get("VideoLocation") %>&VideoName=<%=m.get("VideoName")%>">播放</a></td>
					   <input type="hidden" name="VideoName" value="<%=m.get("VideoName") %>">
					   <input type="hidden" name="name" value="<%=m.get("Name")%>">
					   <td><input class="btn btn-success" type="submit" value="下载"></td>
					   <td><a class="btn btn-danger" href="deletepage.jsp?VideoID=<%=m.get("VideoID")%>&&VideoName=<%=m.get("VideoName") %>" onclick="javascript:if(confirm('确定要删除该视频吗？删除后将无法恢复哦！')){return true;}return false;">删除</a></td>
					  <!--  <td><button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"></button>  -->
					  </form> 
					  </tr>
					  <%
					  }
					  %>
					   </table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					播放界面
				</h4>
			</div>
			<div class="modal-body">
				在这里添加一些文本
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary">
					提交更改
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
	</div>
	
=======
  <form action="searched.jsp" method="post" accept-charset="UTF-8"><input type="text" name="search"><input type="submit" value="搜索"></form>
  <table align="center" width="70%" border="1">
  <caption>我上传的视频</caption>
  <tr><th>序号</th><th>视频ID</th><th>视频名称</th><th>视频关键字</th><th>视频大小</th><th>更新日期</th><th>上传者</th><th>详情</th><th>在线播放</th><th>下载</th><th>删除</th></tr>
   <%
  ArrayList videos = (ArrayList)video.getVideosByName();
  int i=1;
  for(Object o:videos){
  	Map m = (HashMap)o;
   %>
   
   <tr>
   <form action="download.action" method="post" enctype="multipart/form-data">  
   <td><%=i++%></td>
   <td><%=m.get("VideoID")%></td>
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
   <td><a href="deletepage.jsp?VideoID=<%=m.get("VideoID")%>&&VideoName=<%=m.get("VideoName") %>" onclick="javascript:if(confirm('确定要删除该视频吗？删除后将无法恢复哦！')){return true;}return false;">删除</a></td>
   </form> 
   </tr>
   <%
   }
   %>
   <tr><a href="upload.jsp?username=<%=session.getAttribute("username") %>">上传视频</a></tr>
   </table>
>>>>>>> master
  </body>
</html>
