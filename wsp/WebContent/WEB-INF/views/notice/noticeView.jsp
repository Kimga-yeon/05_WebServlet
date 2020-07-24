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
	
	   /* 팝업 레이어 */
   .idSelect {
      cursor: pointer;
   }
   
   .popupLayer {
      position: absolute;
      display: none;
      background-color: #ffffff;
      border: solid 2px #d0d0d0;
      width: 250px;
      height: 250px;
      padding: 10px;
   }
   
   .popupLayer div:nth-of-type(1) {
      position: absolute;
      top: 5px;
      right: 5px
   }
   
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
				 작성자 : <a class="idSelect"><%=notice.getMemberId() %></a> 
				<%-- 작성자 : <%=notice.getMemberId() %>--%>
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
			<%@ include file="../common/reply.jsp" %>
		</div>

	</div>
	<%@ include file="../common/footer.jsp"%>
	
	   <div class="popupLayer">
      <div> <!-- x버튼 영역 -->
         <button type="button" class="close" onClick="closeLayer(this)">
            <span>x</span>
         </button>
      </div>
      <div id="memberInfo">
      	<h4 class = "py-2">admin</h4>
      	<hr>
      	<p>이름 : 김관리 </p>
      	<p>이메일 : admin@kh.or.kr </p>
      	<p>관심분야 : 없음 </p>
      	<p>가입일 : 2020년 7월 3일 </p>
      </div>
   </div>
   
	<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete.do?no=<%=notice.getNoticeNo() %>";
		});
		
		// 팝업 레이어 -----------------------------------------------
		//$(".idSelect").on("click", function(e){
		
			// 동적으로 추가된 요소에도 이벤트를 적용하는 방법 
			$(document).on("click", ".idSelect" , function(e){
			// 매개변수 e : 이벤트 관련 정보가 담겨 있음.
			
			$.ajax({
				//url : "../member/selectMember.do", // 상대/ 절대 경로 작성방법
				url : "<%=request.getContextPath()%>/member/selectMember.do",
				data : {"memberId" : $(this).text()},
				type : "post",
				dataType : "JSON",
				success : function(member){
					console.log(member);
					
					// 관심분야가 없을 경우 "없음" 이라고 표시하기
					var interest;
					if(member.memberInterest == undefined){
						interest = "없음";
					}else{
						interest = member.memberInterest;
					}

					// 이전 회원 정보 삭제
					$("#memberInfo").text("");
					
					// 회원 정보를 출력할 객체 생성 및 데이터 추가
					$h4 = $("<h4>").addClass("py-2").text(member.memberId);
					$hr = $("<hr>");
					$p1 = $("<p>").text("이름 : " + member.memberName);
					$p2 = $("<p>").text("이메일 : " + member.memberEmail);
					$p3 = $("<p>").text("관심분야 : " + interest);
					$p4 = $("<p>").text("가입일 : " + member.memberEnrollDate);
					
					// #memberInfo에 만들어둔 요소들을 자식으로 추가
					$("#memberInfo").append($h4, $hr, $p1, $p2, $p3, $p4);
					
					// 팝업 레이어를 보여주는 함수 호출
					showLayer(e);
					
					
				},error : function(){
					console.log("ajax 통신 실패");
				}
			
			});
		});
		
	      // 팝업 레이어 보여주기
	      function showLayer(e){

	         // 레이어가 나타날 위치를 셋팅한다.
	         var divTop = e.pageY; // 이벤트가 발생한 page y좌표
	         var divLeft = e.pageX; // 이벤트가 발생한 page x좌표

	         $('.popupLayer').css({
	            "top": divTop,
	            "left": divLeft,
	            "position": "absolute"
	         }).show();
	      }
	      
	      
	      // 팝업 레이어 닫기 버튼
	      function closeLayer( obj ) {
	         $(obj).parent().parent().hide();
	      }
		
		
		
	</script>
</body>
</html>
