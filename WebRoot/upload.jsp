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
  
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
  </head>  
    
  <body> 
  <jsp:setProperty name="video" property="name" value="<%=request.getParameter(\"username\") %>"/> 
  <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div class="jumbotron">
			<form action="upload.action" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">
                        <h3>视频上传</h3>
                        <div class="description">
                        	<p>
                         	
                        	</p>
                        </div>
                    </div>
                </div>
				<div class="form-group">
					 <label class="col-sm-2 control-label" >选择上传视频</label>
					 <div class="col-sm-5">
					 	<input type="file" name="myFile" multiple="multiple"  />
					 	<input type="hidden" name="username" value="<%=request.getParameter("username") %>"/> 
					 </div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频关键词</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="keys"/>
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">视频描述</label>
					<div class="col-sm-5">
						<textarea name="comments" class="form-control" rows="5" cols="30"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default">上传</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
  </div>
  </body>  
</html>  