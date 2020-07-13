<%@page import="com.kh.wsp.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// Session에 저장되어있는 login정보를 가져와 loginMember 변수에 저장 
	Member loginMember = (Member)session.getAttribute("loginMember");

	// 쿠키에 저장된 saveId 꺼내오기
	boolean isSave = false; // 아이디 저장 체크박스 값을 수정하기 위한 변수 
	String saveId = ""; // 쿠키에 저장된 아이디를 저장할 변수 
	
	Cookie[] cookies = request.getCookies(); // 요청 주소와 관련된 쿠키를 모두 얻어옴.
	
	// 쿠키에 저장된 아이디 검출 
	if(cookies != null){
		for(Cookie c : cookies){
		
		// 얻어온 쿠키의 이름(key)가 "saveId"와 같은 쿠키를 찾음
		if("saveId".equals(c.getName())){
			saveId = c.getValue(); // saveOD 변수에 쿠키에 저장된 value를 저장 
			isSave = true;
		}
		}
	}
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>WebServer Project</title>
  
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

	<style>
	     body {
	       padding-top: 56px;
	     }
	     
	 </style>
	 <!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
 	 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	 <script>
	 	/*** 이전 페이지에서 전달받은 내용을 alert창으로 표시하기 ***/
	 	
	 	<%
	 	String msg = (String)(request.getSession().getAttribute("msg"));
	 	String status = (String)(request.getSession().getAttribute("status"));
	 	String text = (String)(request.getSession().getAttribute("text"));
	 	%>
	 	
	 	<% if(msg !=null){ %>
		 	swal({
		 		icon : "<%=status%>",
		 		title : "<%=msg%>",
		 		text : "<%=text != null ? text : ""%>"
		 	});
	 	
	 	<%
	 		//Session에 존재하는 특정 키값의 속성 제거
	 		session.removeAttribute("msg");
	 		session.removeAttribute("status");
	 		session.removeAttribute("text");
	 	}
	 	%>
	 </script>
</head>

<body>
	
	<!-- Navigation으로 된 header -->
	<div class="header navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<%=request.getContextPath()%>">WebServer Project</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
				  <li class="nav-item">
				    <a class="nav-link" href="<%=request.getContextPath()%>/notice/list.do">Notice</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="<%=request.getContextPath()%>/noticeEx/list.do">Notice(exercise)</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#">Board</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#">Board(exercise)</a>
				  </li>
				  
				  <%-- 세션에서 login 정보를 얻어 왔는데 null이었다 == 로그인이 안되어 있을 때 --%>
				  <% if(loginMember == null) {%>
				  <li class="nav-item active">
				    <a class="nav-link" data-toggle="modal" href="#modal-container-1">Login</a>
				  </li>
				  <% }else{ %>
				  <li class="nav-item active">
				    <a class="nav-link"  href="<%=request.getContextPath()%>/member/myPage.do"><%= loginMember.getMemberName() %></a>
				  </li>
				  <li class="nav-item active">
				    <a class="nav-link"  href="<%=request.getContextPath()%>/member/logout.do">Logout</a>
				  </li>
				  <% } %>
				  
				  
				</ul>
			</div>
		</div>
	</div>
  
  
	<div class="modal fade" id="modal-container-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">로그인 모달창</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">						<!-- 요청 주소 중 최상위 주소 (/wsp)를 얻어옴 -->
					<form class="form-signin" method="POST" action="<%=request.getContextPath()%>/member/login.do"
						onsubmit="return loginValidate();">
						<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디"
						value="<%=saveId%>"> <br>
							
						<input type="password" class="form-control" id="memberPwd" name="memberPwd"
							placeholder="비밀번호"> <br>
						<div class="checkbox mb-3">
							<label> 
							
								<input type="checkbox" name="saveId" <%=isSave ? "checked" : "" %>> 아이디 저장
								
							</label>
						</div>
						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
						<a class="btn btn-lg btn-secondary btn-block" 
							href="<%=request.getContextPath()%>/member/signUpForm.do">회원가입</a>
																			
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Bootstrap core JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

	<script>
		// 로그인 유효성 검사
		// -> 아이디 , 비밀번호 중 하나라도 입력되지 않으면 form태그 기본 이벤트를 제거한다.
		function loginValidate(){
			if($("#memberId").val().trim() == ""){
				alert("아이디를 입력해 주세요.")
				$("#memberId").focus();
				return false; // 기본 이벤트를 제거
			}
			if($("#memberPwd").val().trim() == ""){
				alert("비밀번호를 입력해 주세요.")
				$("#memberPwd").focus();
				return false; // 기본 이벤트를 제거
			}
			return true;
			
			
		}
	</script>


</body>

</html>