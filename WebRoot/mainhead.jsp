@ -20,9 +20,17 @@ String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.
  </head>
  
  <body>
  <jsp:useBean id="video" class="beans.Videos"></jsp:useBean>
  <jsp:setProperty name="video" property="name" value="<%=request.getParameter(\"username\") %>"/>
  <%
  String username=session.getAttribute("username").toString();
   %>
  <h3><a href="main.jsp?username=<%=username%>">回到主页    </a>   欢迎<a href="userinfo.jsp?username=<%=username%>"><%=session.getAttribute("username") %></a>,<a href="logout.jsp">退出登录</a></h3>
  <tr><td><a href="AllVideo.jsp?username=<%=username%>">所有视频</a></td>
  	  <td><a href="QueriedPage.jsp?username=<%=username %>&&keys=考研">考研</a></td>
  	  <td><a href="QueriedPage.jsp?username=<%=username %>&&keys=视频">视频</a></td>
  
  
  </tr>
  </body>
</html>