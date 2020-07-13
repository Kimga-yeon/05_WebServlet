<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.wsp.notice.model.vo.Notice"%>
<% Notice notice = (Notice)request.getAttribute("notice");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	#notice-area{ margin-bottom:200px;}
	#notice-content{ padding-bottom:150px;}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="container my-5">

		<div id="notice-area">

			<!-- Title -->
			<h1 class="mt-4"><%=notice.getNoticeTitle() %></h1>

			<!-- Writer -->
			<p class="lead">
				<%-- 작성자 : <a href="#"><%=notice.getNoticeWriter() %></a> --%>
				작성자 : <%=notice.getMemberId() %>
			</p>

			<hr>

			<!-- Date -->
			<p>
				<%=notice.getNoticeModifyDate() %>
				 <span class="float-right">조회수  <%=notice.getReadCount() %></span>
			</p>

			<hr>


			<!-- Content -->
			<div id="notice-content"><%=notice.getNoticeContent() %></div>
			
			
			<hr>
			
			<div>
				<% if(loginMember != null && (notice.getMemberId().equals(loginMember.getMemberId()))) {%>
				<button class="btn btn-primary float-right" id="deleteBtn">삭제</button>
				<a href="updateForm.do?no=<%=notice.getNoticeNo() %>" class="btn btn-primary float-right ml-1 mr-1">수정</a>
				<% } %>
				
				<a href="list.do" class="btn btn-primary float-right">목록으로</a>
			</div>
		</div>

	</div>
	<%@ include file="../common/footer.jsp"%>
	
	<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete.do?no=<%=notice.getNoticeNo() %>";
		});
	</script>
</body>
</html>
