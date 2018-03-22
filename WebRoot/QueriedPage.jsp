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
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-table.css">
	<script src="js/MainTable.js"></script>

  </head>
  
  <body>
  <jsp:setProperty name="video" property="videokeys" value="<%=request.getParameter(\"keys\") %>"/>
	<div class="container">
		<div class="row clearfix">
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
					        <th>在线播放</th>
					    </tr>
					    </thead>
					  <%
					 ArrayList videos = (ArrayList)video.getVideosByVideoKeys();
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
					   <td><a class="btn btn-primary" onclick="getValue(this)"  data-toggle="modal" data-target="#myModal" name="<%=m.get("VideoLocation") %>/<%=m.get("VideoName")%>">播放</a></td>
					  <input type="hidden" name="VideoName" value="<%=m.get("VideoName") %>">
					  <input type="hidden" name="name1" value="<%=m.get("Name")%>">
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
				<video width="480" height="360" controls class="center" id="videoid">
				<source id="videoidsrc" src="" type="video/mp4">
				</video>
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

  </body>
  <script>
    function getValue(a){
     var dsr=document.getElementById("videoid").src="http://localhost:8080/SSW/stream?fpath=/ssw/"+a.name;
    }
  	//var oBtn = document.getElementById('')
  </script>
</html>
