<%@ page language="java" import="java.util.*" import="beans.Users" pageEncoding="UTF-8"%>
<%@ include file="mainhead.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=session.getAttribute("username") %>的详细信息</title>
    
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
  <jsp:useBean id="user" class="beans.Users"></jsp:useBean>
  <jsp:setProperty name="user" property="name"  value="<%=request.getParameter(\"username\") %>"/>
  <%
  ArrayList users = (ArrayList)user.getUserByName();
  for(Object o:users){
  	Map m = (HashMap)o;
  %>
    <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div class="jumbotron">
			<form action="modification.action" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">
                        <h3><%=session.getAttribute("username") %>的个人信息</h3>
                        <div class="description">
                        	<p>
                         	
                        	</p>
                        </div>
                    </div>
                </div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-5">
						<input type="text" name="name" class="form-control" value="<%=m.get("Name") %>" readonly="true">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-5">
						<input type = "text" class="form-control" name = "sex" value = "<%=m.get("Sex") %>">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-5">
						<input type="text" name="telephone" value="<%=m.get("Telephone") %>" class="form-control">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">专业</label>
					<div class="col-sm-5">
						<input type="text" name="major" value="<%=m.get("Major") %>" class="form-control">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">班级</label>
					<div class="col-sm-5">
						<input type="text" name="class1" value="<%=m.get("Class") %>" class="form-control">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-5">
						<input type="text" name="truename" value="<%=m.get("TrueName") %>" class="form-control">
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">Email</label>
					<div class="col-sm-5">
						<input type="text" name="email" value="<%=m.get("Email") %>" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default">修改</button>
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
  </body>
</html>
