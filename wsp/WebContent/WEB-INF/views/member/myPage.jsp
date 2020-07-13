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
		<%
			// header.jsp에서 Member가 import 되어 있으므로 현재 페이지에서 import할 필요 없음.
			// header.jsp에 이미 로그인 정보가 담겨있는 loginMember 변수가 선언되어 있음.
			//Member loginMember= (Member)request.getAttribute("member");
		
		
			// 하나의 String으로 DB에 저장된 전화번호, 주소를
			// 화면에 알맞게 나눠 작성하기 위해 쪼개주는 작업 진행
			
			String[] phone = loginMember.getMemberPhone().split("-");
			String[] address = loginMember.getMemberAddress().split(",");
			
			
	         // 주소 중 일부 내용이 비어있을 경우
	         int aLength = 0;
	         if(address.length != 0) aLength = address.length;
	         
	         if(aLength < 3){
	            String[] newArr = new String[3];
	            
	            for(int i=0;i<aLength ;i++){
	               newArr[i] = address[i];
	            }
	            for(int i=aLength; i< newArr.length ; i++){
	               newArr[i] = "";
	            }
	            address = newArr;
	         }
			
			
			
			String[] checked = new String[6];
			
			// 관심분야가 체크 된적이 있을 경우
			if(loginMember.getMemberInterest() != null){
				String[] arr = loginMember.getMemberInterest().split(",");
				for(int i=0; i<arr.length; i++){
					switch(arr[i]){
					case "운동" : checked[0] = "checked"; break;
					case "영화" : checked[1] = "checked"; break;
					case "음악" : checked[2] = "checked"; break;
					case "요리" : checked[3] = "checked"; break;
					case "게임" : checked[4] = "checked"; break;
					case "기타" : checked[5] = "checked"; break;
					}
				}
			}
		%>

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
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id"><%=loginMember.getMemberId() %></h5>
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
	
						<!-- 전화번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="phone1">전화번호</label>
							</div>
							<!-- 전화번호1 -->
							<div class="col-md-3">
								<select class="custom-select" id="phone1" name="phone1">
									<option>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>019</option>
								</select>
							</div>
							<script>
								$.each($("#phone1 > option"),function(index, item){
									// index : 현재 접근중인 인덱스 번호
									// item : 현재 접근중인 요소
									
									if($(item).text() == "<%=phone[0]%>"){
										// 현재 접근한 요소의 내용이
										// phone[0] 인덱스 값과 같다면 
										
										$(item).prop("selected",true);
										
									}
								});
							</script>
							
	
							<!-- 전화번호2 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone2" name="phone2" maxlength="4" value="<%=phone[1]%>">
							</div>
	
							<!-- 전화번호3 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone3" name="phone3" maxlength="4" value="<%=phone[2]%>">
							</div>
						</div>
	
						<!-- 이메일 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="memberEmail">Email</label>
							</div>
							<div class="col-md-6">
								<input type="email" class="form-control" id="email" name="email" value="<%= loginMember.getMemberEmail()%>">
						</div>
						</div>
						<br>
	
						<!-- 주소 -->
						<!-- 오픈소스 도로명 주소 API -->
						<!-- https://www.poesis.org/postcodify/ -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="postcodify_search_button" >우편번호</label>
							</div>
							<div class="col-md-3">
								<input type="text" name="post" class="form-control postcodify_postcode5" value="<%=address[0]%>">
							</div>
							<div class="col-md-3">
								<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
							</div>
						</div>
	
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="address1" >도로명 주소</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control postcodify_address" name="address1" id="address1"  value="<%=address[1]%>">
							</div>
						</div>
	
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="address2" >상세주소</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control postcodify_details" name="address2" id="address2"  value="<%=address[2]%>">
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
	
						<!-- 관심분야 -->
						<hr class="mb-4">
						<div class="row">
							<div class="col-md-3">
								<label>관심 분야</label>
							</div>
							<div class="col-md-9 custom-control custom-checkbox">
								<div class="form-check form-check-inline">
									<input type="checkbox" name="memberInterest" id="sports"
										value="운동" class="form-check-input custom-control-input" <%=checked[0] %> >
									<label class="form-check-label custom-control-label"
										for="sports">운동</label>
								</div>
								<div class="form-check form-check-inline">
									<input type="checkbox" name="memberInterest" id="movie"
										value="영화" class="form-check-input custom-control-input" <%=checked[1] %>>
									<label class="form-check-label custom-control-label" for="movie" >영화</label>
								</div>
								<div class="form-check form-check-inline">
									<input type="checkbox" name="memberInterest" id="music"
										value="음악" class="form-check-input custom-control-input" <%=checked[2] %>>
									<label class="form-check-label custom-control-label" for="music">음악</label>
								</div>
								<br>
								<div class="form-check form-check-inline">
									<input type="checkbox" name="memberInterest" id="cooking"
										value="요리" class="form-check-input custom-control-input" <%=checked[3] %>>
									<label class="form-check-label custom-control-label"
										for="cooking" <%=checked[3] %>>요리</label>
								</div>
								<div class="form-check form-check-inline">
									<input type="checkbox" name="memberInterest" id="game"
										value="게임" class="form-check-input custom-control-input" <%=checked[4] %>>
									<label class="form-check-label custom-control-label" for="game" >게임</label>
								</div>
								<div class="form-check form-check-inline">
									<input type="checkbox" name="memberInterest" id="etc"
										value="기타" class="form-check-input custom-control-input" <%=checked[5] %>> 
									<label class="form-check-label custom-control-label" for="etc" >기타</label>
								</div>
							</div>
						</div>
	
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
			
			
			// 전화번호 관련
		 	$(".phone").on("input",function(){
		 		
				// 전화번호 input 태그에 4글자 이상 입력하지 못하게 하는 이벤트
                if ($(this).val().length > $(this).prop("maxLength")){
                    $(this).val($(this).val().slice(0, $(this).prop("maxLength")));
                }
            });
			
			
		 	// 전화번호 유효성 검사
            var regExp1 =  /^\d{3,4}$/; // 숫자 3~4 글자
            var regExp2 =  /^\d{4,4}$/; // 숫자 4 글자
            
            if(!regExp1.test($phone2.val()) || !regExp2.test($phone3.val())){
				singUpCheck.phone3 = false;
            }else{
				singUpCheck.phone3 = true;
			}
            
            
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
