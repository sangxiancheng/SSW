<html>
  <head>
    <title>登录处理</title>
  </head>
  <body>
  <%
  request.setCharacterEncoding("UTF-8");
  String name=request.getParameter("name");
  String pw=request.getParameter("pw");
  if(name!=null&&pw!=null&&name.equals("admin")&&pw.equals("123"))
  {
  response.sendRedirect("success.jsp?name="+name);
  }
  else
  {
  out.println("<h3>用户名或密码错误，5秒后自动返回...<a href=\"login.html\">登录页面</a>!</h3>");
  response.setHeader("Refresh","5;url=login.html");
  }
   %>
  </body>
</html>
