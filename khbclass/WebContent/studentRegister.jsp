<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>학생등록처리</h1>
<%
    String nai = request.getParameter("nai");
    String irum = request.getParameter("irum");
    String hakbun = request.getParameter("hakbun");
%>
<%=nai %>:<%=irum %>:<%=hakbun %>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://khgthree.cafe24.com:3306/khgthree", "khgthree", "wjdqhrydbrdnjs3");
Statement stmt = conn.createStatement();
String sql = "insert into EBstudent(age,irum,hakbun) values('"+nai+"','"+irum+"','"+hakbun+"')";
int cnt = stmt.executeUpdate(sql);
%>
<%=cnt %>건 학생이 등록되었습니다.
</body>
</html>