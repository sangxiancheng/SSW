<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="Users" class="beans.Users" />
<jsp:setProperty name="Users" property="*" />
<%
	int result = Users.addStudent();
	String msg = "注册失败！";
	if(result == 1){
		msg = "注册成功！";
	}
%>
<script>window.alert('<%=msg %>');</script>

