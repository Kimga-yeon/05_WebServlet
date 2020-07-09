<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
	<form method="post" 
	action="<%=request.getContextPath()%>/board/boardWrite.do">
	
	제목 : <input type="text" name="title"> <br>
	내용 : <br>
	<textarea name="content" rows="3" cols="30" style="resize:none"></textarea>
	<br>
	<button>글 등록하기</button>
	<button type="button" onclick="history.back()">이전 페이지로 이동</button>
	</form>
</body>
</html>