<%@page import="com.kh.test.model.vo.TestBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%
	List<TestBoard> list = (List<TestBoard>)request.getAttribute("list");    
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h1>게시판</h1>
	<table border="1">
		<thead>
			<tr>
				<th>순서</th>
				<th>글번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
			</tr>	
		</thead>
		
		<tbody>
			<% for(int i=0; i<list.size(); i++){ %>
			<tr>
				<td><%= i+1 %></td>
				<td><%= list.get(i).getBoardNo() %></td>
				<td><%= list.get(i).getTitle() %></td>
				<td><%= list.get(i).getContent() %></td>
				<td><%= list.get(i).getCreateDate() %></td>
			</tr>
			<% } %>		
		</tbody>
	</table>
	<button onclick="location.href='writeForm.do'">글쓰기</button>
</body>
</html>