<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피자 주문 화면</title>
<style>
	table{
		border : 1pz solid black;
		border-collapse : collapse;
	}
	
	th, td{
		border : 1px solid black;
		padding : 10px;
	}
</style>
</head>
<body>
<!-- 오늘 날짜를 외부 jsp파일 include해서 표현 -->

<!-- 메뉴판 -->

<!-- 메뉴를 주문하기 위한 from 태그 -->
<form action="/2_JSP/confirmPizza.do" method="POST">


	<button>주문하기</button>
</form> 
	

</body>
</html>