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
  <jsp:setProperty name="video" property="videoid" value="<%=request.getParameter(\"VideoID\") %>"/>
   <%
   HashMap videoinfo = (HashMap)video.getVideoByVideoID();
   if(videoinfo!=null){
   %>
    <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div class="jumbotron">
			<form action="download.action" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">
                        <h3>视频详细信息</h3>
                        <div class="description">
                        	<p>
                         	
                        	</p>
                        </div>
                    </div>
                </div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频        I  D</label>
					<div class="col-sm-5">
						<input type="text" name="name" class="form-control" value= "<%=videoinfo.get("VideoID") %>" readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频名称</label>
					<div class="col-sm-5">
						<input type = "text" class="form-control" name = "sex" value = "<%=videoinfo.get("VideoName") %>"  readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频关键字</label>
					<div class="col-sm-5">
						<input type="text" name="telephone" value="<%=videoinfo.get("VideoKeys") %>" class="form-control"  readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频描述</label>
					<div class="col-sm-5">
						<input type="text" name="telephone" value="<%=videoinfo.get("VideoComments") %>" class="form-control"  readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频大小</label>
					<div class="col-sm-5">
						<input type="text" name="major" value="<%=videoinfo.get("VideoSize") %>" class="form-control"  readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频更新日期</label>
					<div class="col-sm-5">
						<input type="text" name="class1" value="<%=videoinfo.get("date(VideoDate)") %>" class="form-control"  readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">上传者</label>
					<div class="col-sm-5">
						<input type="text" name="truename" value="<%=videoinfo.get("Name") %>" class="form-control"  readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">操作</label>
					<div class="col-sm-5">
						<a class="btn btn-default" href="MyStream.jsp?VideoLocation=<%=videoinfo.get("VideoLocation") %>&VideoName=<%=videoinfo.get("VideoName")%>">播放</a>
						<input type="hidden" name="VideoName" value="<%=videoinfo.get("VideoName") %>">
						<input type="hidden" name="name" value="<%=videoinfo.get("Name")%>">
						<button type="submit" class="btn btn-default">下载</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
  </div>
  
   <%
   }
    %>
   </table>
   </form>
  </body>
</html>
