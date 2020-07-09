<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" errorPage="error.jsp">
<title>페이지 지시어(import, errorPage)</title>
</head>
<body>
	<h2>import</h2>
	<p>List, ArrayList import 하기</p>
	
	<%
		List<String> list = new ArrayList<String>();
	
		list.add("부대찌개");
		list.add("햄버거");
		list.add("백반");
		list.add("샐러드");
		
		// 강제 에러 발생
		// list.add(10, "서브웨이");
	%>
	
	<h3>list에 저장된 내용 단순 출력</h3>
	0번 인덱스 : <%= list.get(0) %> <br>
	1번 인덱스 : <%= list.get(1) %> <br>
	2번 인덱스 : <%= list.get(2) %> <br>
	3번 인덱스 : <%= list.get(3) %> <br>
	
	<hr>
	
	<h3>Scriptlet + Expression + HTML</h3>
	
	<% for(int i=0; i<list.size(); i++){ %>
		
		<%=i%>번 인덱스 : <%= list.get(i) %> <br>
		
		
	<% } %>
	
	<hr> 
	
	<h3>Scriptlet + Expression + Javascript </h3>
	<button onclick="test()";>확인하기</button>
	<div id="testArea"></div>
	
	<script>
		function test(){
			var testArea = document.getElementById("testArea");
			
			// 버튼 클릭 시 이전 내용 삭제
			testArea.innerHTML = "";
			
			<% for(int i=0; i<list.size(); i++){ %>
			
				testArea.innerHTML += "<%=i%>번 인덱스 : <%=list.get(i)%><br>";
			<% } %>
		}
	
	</script>
	
	

</body>
</html>