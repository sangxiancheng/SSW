<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
    <base href="<%=basePath%>">    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

  </head>
  
  <body>
  <jsp:useBean id="video" class="beans.Videos"></jsp:useBean>
  <jsp:setProperty name="video" property="name" value="<%=request.getParameter(\"username\") %>"/>
  <%
  String username=session.getAttribute("username").toString();
   %>
	<div class="container">
		<h1 style="color:Gainsboro">Video uploading and downloading platform</h1>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" >视频平台</a>
					</div>
					
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li >
								 <a href="main.jsp?username=<%=username%>">我的视频</a>
							</li>
							<li>
								 <a href="main.jsp">主页</a>
							</li>
							<li class="dropdown">
								 <a href="#" class="dropdown-toggle" data-toggle="dropdown">分类导航<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li>
										 <a href="QueriedPage.jsp?username=<%=username %>&&keys=考研">考研</a>
									</li>
									<li>
										 <a href="QueriedPage.jsp?username=<%=username %>&&keys=数学">数学</a>
									</li>
									<li>
										 <a href="QueriedPage.jsp?username=<%=username %>&&keys=英语">英语</a>
									</li>
								</ul>
							</li>
						</ul>
						<form action="searched.jsp?search=" method="post" class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" name="search" class="form-control">
							</div> <input type="submit" class="btn" value="搜索"></button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li>
								 <a>欢迎登录，<%=username %></a>
							</li>
							<li class="dropdown">
								 <a href="#" class="dropdown-toggle" data-toggle="dropdown">账户管理<strong class="caret"></strong></a>
								<ul class="dropdown-menu" width="50%">
									<li>
										 <a href="userinfo.jsp?username=<%=username%>">个人信息</a>
									</li>
									<li class="divider">
									</li>
									<li>
										 <a href="logout.jsp">退出登录</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					
				</nav>
			</div>
		</div>
	</div>
          <!-- Javascript -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
		<script src="assets/bootstrap/js/bootstrap-table.js"></script>
		<!-- put your locale files after bootstrap-table.js -->
		<script src="assets/bootstrap/js/bootstrap-table-zh-CN.js"></script>
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
  </body>
</html>
