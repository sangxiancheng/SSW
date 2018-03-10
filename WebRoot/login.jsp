<%@ page pageEncoding= "UTF-8" %>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录处理</title>
  </head>
  <body>
  <jsp:useBean id="Users" class="beans.Users" />
  <jsp:setProperty name="Users" property="*" />
  <%

  String name=request.getParameter("name");
  String pw=request.getParameter("password");
  String result = Users.getPw();
  if(name!=null&&pw!=null&&pw.equals(result))
  {
  response.sendRedirect("main.jsp?name="+name);
  }
  else
  {
  request.setCharacterEncoding("UTF-8");
  out.println("<h3>用户名或密码错误，5秒后自动返回...<a href=\"login.html\">登录页面</a>!</h3>");
  response.setHeader("Refresh","5;url=login.html");
  }
   %>
  </body>
</html>
