<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
    
<%
	Date now = new Date(); // Date 객체가 생성 되면서 현재 시간 저장함.
	String nowFormat = String.format("%tY년 %tm월 %td일 %tA", now, now, now, now);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= nowFormat %>
</body>
</html>