<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현재 시간</title>
</head>
<body>
	<h1>
		현재 시간 : 
		<span style="color:blue">
			<!-- 현재 시간을 나타내는 jsp를 include 할 것 -->
			<%@ include file="currentTime.jsp" %>
		</span>
	</h1>
</body>
</html>