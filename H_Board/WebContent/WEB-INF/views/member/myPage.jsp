<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
<style>
	input[type="number"]::-webkit-outer-spin-button, 
	input[type="number"]::-webkit-inner-spin-button
		{
		-webkit-appearance: none;
		margin: 0;
	}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		

		<div class="row my-5">
			<%@ include file="sideMenu.jsp"%>
			
			<div class="col-sm-8">
				<h3>My Page</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updateMember.do" onsubmit="return validate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>이메일</h6>
							</div>
							<div class="col-md-6">
								<h5 id="email"><%=loginMember.getMemberEmail() %></h5>
							</div>
						</div>
	
						<!-- 이름 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>이름</h6>
							</div>
							<div class="col-md-6">
								<h5 id="name"><%=loginMember.getMemberName()%></h5>
							</div>
						</div>
						
						<!-- 닉네임 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>닉네임</h6>
							</div>
							<div class="col-md-6">
								<h5 id="nick"><%=loginMember.getMemberNick()%></h5>
							</div>
						</div>
	
						

						
	
						<!-- jQuery와 postcodify 를 로딩한다. -->
						<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
						<script>
							// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
							$(function() {
								$("#postcodify_search_button").postcodifyPopUp();
							});
						</script>
	
					
	
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	<%@ include file="../common/footer.jsp"%>
		

	<script type="text/javascript">
		$(".phone").on("input", function() {
			if ($(this).val().length > $(this).prop("maxLength")) {
				$(this).val($(this).val().slice(0,$(this).prop("maxLength")));
			}
		});
	</script>
	
	<script>
        // 각 유효성 검사 결과를 저장할 객체
        var singUpCheck = { 
				"phone3":false,
				"email":false
				};
        
		// submit 동작
		function validate(){
			var $phone2 = $("#phone2");
			var $phone3 = $("#phone3");
			var $email = $("#email");
			
			
			
			
		
            
            
         	// 이메일 유효성 검사
			var regExp3 =  /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
			
			if(!regExp3.test($email.val())){ 
				singUpCheck.email = false;
			}else{
				singUpCheck.email = true;
			}
            
			for(var key in singUpCheck){
				if(!singUpCheck[key]){
					alert("일부 입력값이 잘못되었습니다.");
					var id = "#"+key;
					$(id).focus();
					return false;
				}
			}
		}
       </script>
</body>
</html>
