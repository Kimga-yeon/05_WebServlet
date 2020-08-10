<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<style>
		/* number 태그 화살표 제거 */
	    input[type="number"]::-webkit-outer-spin-button,
	    input[type="number"]::-webkit-inner-spin-button {
	        -webkit-appearance: none;
	        margin: 0;
	    }
	</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<div class="py-5 text-center">
		<h2>회원 가입</h2>
	</div>
	
	<div class="row">
		<div class="col-md-4 offset-md-4">
		
		<form method="POST" action="signUp.do" class="needs-validation" name="signUpForm" onsubmit="return validate();">
		
		
			<!-- 이메일 -->
			<div class="row mb-3 form-row">
				<div class="col-md-3">
					<label for="email">* Email</label>
				</div>
				<div class="col-md-6">
				 <input type="email" class="form-control" id="email" name="email" autocomplete="off" required>
				</div>
				<div class="col-md-6 offset-md-3">
					<span id="checkEmail">&nbsp;</span>
				</div>
			</div>
			<br>
			

			<!-- 이름 -->
			<div class="row mb-3 form-row">
				<div class="col-md-3">
					<label for="name">* 이름</label>
				</div>
				<div class="col-md-6">
					<input type="text" class="form-control" id="name" name="name"
						required>
				</div>

				<div class="col-md-6 offset-md-3">
					<span id="checkName">&nbsp;</span>
				</div>
			</div>
			
			<!-- 닉네임 -->
            <div class="row mb-3 form-row">
                <div class="col-md-3">
                    <label for="nick">* 닉네임</label>
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="nick" name="nick" required>
                </div>
                
                <div class="col-md-6 offset-md-3">
                    <span id="checkNick">&nbsp;</span>
                </div>
            </div>
            
            
           <!-- 비밀번호 -->
           <div class="row mb-3 form-row">
               <div class="col-md-3">
                   <label for="pwd1">* 비밀번호</label>
               </div>
               <div class="col-md-6">
                   <input type="password" class="form-control" id="pwd1" name="pwd1" maxlength="12" placeholder="비밀번호를 입력하세요" required>
               </div>
               
               <div class="col-md-6 offset-md-3">
                   <span id="checkPwd1">&nbsp;</span>
               </div>
           </div>

           <!-- 비밀번호 확인 -->
           <div class="row mb-3 form-row">
               <div class="col-md-3">
                   <label for="pwd2">* 비밀번호 확인</label>
               </div>
               <div class="col-md-6">
                   <input type="password" class="form-control" id="pwd2" maxlength="12" placeholder="비밀번호 확인" required>
               </div>
               
               <div class="col-md-6 offset-md-3">
                   <span id="checkPwd2">&nbsp;</span>
               </div>
           </div>
           <br>
				
				
				
			<!-- 성별 -->	
			<hr class="mb-4">
			<div class="row">
				<div class="col-md-3">
					<label>* 성별</label>
				</div>
				<div class="col-md-9 custom-control custom-radio">
					<div class="form-check form-check-inline">
					<input type="radio" name="memberGender" id="male" value="a"
					class="form-check-input custom-control-input">
					<label class="form-check-label custom-control-label" for="male">남</label>
					</div>
					<div class="form-check form-check-inline">
					<input type="radio" name="memberGender" id="female" value="b"
					class="form-check-input custom-control-input">
					<label class="form-check-label custom-control-label" for="female">여</label>
					</div>
				</div> 
			</div>
			
			<hr class="mb-4">
			<button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
		</form>
		<br><br>
	</div>
	</div>
		
		
		
		
		
		<script>
		// 각 유효성 검사 결과를 저장할 객체
		var signUpcheak = {
				"email":false,
				"name":false,
				"nick":false,
				"pwd1":false,
				"pwd2":false,
				"gender":false
			};
		
		
		// ----------------실시간 유효성 검사-------------------/
			// 정규표현식 
			
			// jQuery 변수 : 변수에 직접적으로 제이쿼리 메소드 사용가능.
			var $email = $("#email");
			var $name = $("#name");
			var $nick = $("#nick");
			var $pwd1 = $("#pwd1");
			var $pwd2 = $("#pwd2");
			var $gender = $("#gender");
			
			
			// 이메일 유효성 검사 
			// 영어 대/소문자, 숫자, 총 길이  
			$email.on("input", function(){
				var regExp =  /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 4글자 아무단어 @ 아무단어 . * 3아무3
				
				if(!regExp.test($(this).val())){ // 입력한 이름이 유효하지 않은 경우
					$("#checkEmail").text("유효하지 않은 이메일 형식입니다.").css("color","red");
					signUpCheck.email = false;
				}else{
					$("#checkEmail").text("유효한 이메일 입니다.").css("color","green");
					signUpCheck.email = true;
				}
			});
			

			// 이름 유효성 검사
			$name.on("input", function(){
				var regExp =  /^[가-힣]{2,8}$/; // 한글 두 글자 이상
				
				if(!regExp.test($(this).val())){ // 입력한 이름이 유효하지 않은 경우
					$("#checkName").text("한글 두 글자 이상을 입력하세요.").css("color","red");
					signUpCheck.name = false;
				}else{
					$("#checkName").text("유효한 이름 형식입니다.").css("color","green");
					signUpCheck.name = true;
				}
				
				
			});
			
			
			// 닉네임 유효성 검사
			// 영어 대/소문자,한글,숫자 총길이
			$nick.on("input", function(){
				var regExp =  /^[가-힣a-zA-Z]{2,9}$/; // 한글 두 글자 이상
				
				if(!regExp.test($(this).val())){ // 입력한 이름이 유효하지 않은 경우
					$("#checkNick").text("유효하지 않은 닉네임 입니다.").css("color","red");
					signUpCheck.nick = false;
				}else{
					$("#checkNick").text("유효한 닉네임 입니다.").css("color","green");
					signUpCheck.nick = true;
				}
			});
			
			
			
			// 비밀번호 유효성 및 일치 검사
			$pwd1.on("input",function(){
				
				var regExp = /^[A-Za-z0-9]{6,12}$/;
				
				if(!regExp.test($("#pwd1").val())){
					$("#checkPwd1").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
					signUpCheck.pwd1 = false;
				}else{
					$("#checkPwd1").text("유효한 비밀번호 형식입니다.").css("color","green");
					signUpCheck.pwd1 = true;
				}
				
				if(!signUpCheck.pwd1 && $pwd2.val().length > 0){
					swal("유효한 비밀번호를 작성해 주세요.");
					$pwd2.val("");
					$pwd1.focus();
				}else if(signUpCheck.pwd1 && $pwd2.val().length > 0){
					if($("#pwd1").val().trim() != $("#pwd2").val().trim()){
						$("#checkPwd2").text("비밀번호 불일치").css("color","red");
						signUpCheck.pwd2 = false;
					}else{
						$("#checkPwd2").text("비밀번호 일치").css("color","green");
						signUpCheck.pwd2 = true;
					}
				}
			});
			
			// submit 동작
			function validate(){
				
				for(var key in signUpCheck){
					if(!signUpCheck[key]){
						
						var msg;
						switch(key){
						case "email" : msg="이메일이"; break;
						case "name" : msg= "이름이"; break;
						case "nick" : msg="닉네임이"; break;
						case "pwd1" : case "pwd2" : msg="비밀번호가"; break;
						case "gender": msg="성별이"; break;
						}

					alert(msg + "유효하지 않습니다.");
					var el = "#" + key;
					$(el).focus();
					return false;
					}
				}
			}
			
			
			
		</script>
		
		<%@ include file="../common/footer.jsp" %>
			
	</body>
</html>