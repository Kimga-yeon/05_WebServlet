//1. 버튼 클릭 시 GET 방식으로 서버에 입력값 전송 및 응답
$("#jqBtn1").on("click", function () {
	var input = $("#input1").val();

	$.ajax({
		// url : 데이터를 전송할(요청할) url (필수 속성!) 
		url: "jqAjax1.do",

		// data : 요청 시 전달할 파라미터 설정
		data: { "input": input },
		// key = "input" , value = input(입력받은 값)

		// type : 전송 방식(GET, POST, DELETE)
		type: "GET",

		// success : Ajax 통신 성공 시 처리할 함수를 지정하는 속성 
		success: function (result) {
			// 매개변수 result는 응답되는 데이터를 담고있으며
			// 매개변수명은 자유롭게 지정 가능함.

			console.log("Ajax 통신 성공");
			$("#result-area").text(result);

		},

		// error : Ajax 통신 실패 시 처리할 함수 지정
		error: function (request, status, error) {
			// request : 요청 주소
			// status : HTTP 응답 상태 코드(200,404,500 등등)
			// error : 에러 내용 
			console.log("ajax 통신 오류");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		},

		// complete : 통신 성공 여부와 관계없이 실행
		complete: function () {
			console.log("무조건 호출되는 함수");
		}


	})
});

//2. 버튼 클릭 시 POST 방식으로 서버에 입력값 전송 및 응답
$("#jqBtn2").on("click", function () {
	var input = $("#input2").val();

	$.ajax({
		url: "jqAjax2.do",
		data: { "input": input },
		type: "POST",
		success: function (result) {
			console.log("Ajax 통신 성공");
			$("#result-area").text(result);
		},


		error: function (request, status, error) {
			console.log("ajax 통신 오류");
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		},


		complete: function () {
			console.log("무조건 호출되는 함수");
		}


	})
});

//비동기 통신을 이용한 실시간 아이디 중복 검사
$("#inputId").on("input", function(){

	$.ajax({
		url : "member/idDupCheck.do",
		data : {"inputId" : $('#inputId').val() },
		type : "POST" , 
		dataType : "text", // 서버의 응답 데이터 형식을 지정하는 속성.
		// 미작성 시 자동으로 응답데이터 형식을 판단하여 지정됨.
		success : function(result){
			if(result == 0){
				$("#idDupResult").text("사용 가능한 아이디입니다.").css("color","green");
			}else{
				$("#idDupResult").text("이미 사용중인 아이디입니다").css("color","red");
			}

		}, error : function(request, status, error){
			console.log("ajax 통신 오류");
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

		}
	})
});


//3. 서버로 기본형 데이터 전송 후, 응답을 개체(Object)로 받기
$("#jqBtn3").on("click",function(){

	var input = $("#input3").val();

	$.ajax({
		url : "jqAjax3.do",
		data : {"input" : input},
		type : "get", 
		// 응답되는 데이터가 JSON 형태임을 인식 시키는 방법3
		// -> ajax 통신 응답 데이터의 타입을 "json"으로 지정
		dataType : "json",		
		success : function(user){
			// 응답되는 데이터가 JSON 형태임을 인식 시키는 방법2
			// -> JSON.parse()를 이용해
			//    JSON 형태의 문자열을 JSON(자바스크립트 객체)으로 인식할 수 있도록 변환
//			user = JSON.parse(user);

			console.log(user);

			var result = "";

			if(user != null){
				result = "번호 : " + user.no + "<br>"
				+ "이름 : " + user.name + "<br>"
				+ "나이 : " + user.age + "<br>"
				+ "성별 : " + user.gender + "<br>"
			}else{
				result = "사용자 정보가 없습니다.";
			}
			$("#result-area").html(result);

		},error : function(){
			console.log("ajax 통신 실패");
		}
	});

});





//	4. 입력받은 아이디와 일치하는 회원 정보 조회하기
$("#selectMemberBtn").on("click",function(){
	var input = $("#inputId2").val();

	$.ajax({
		url : "jqAjax4.do",
		data : {"input" : input},
		type : "get", 
		dataType : "json",	
		
		success : function(member){
			//user = JSON.parse(user);
			console.log(member);

			var result = "";

			if(member != null){
				result = "아이디 : " + member.memberId + "<br>"
				+ "이름 : " + member.memberName + "<br>"
				+ "이메일 : " + member.memberEmail + "<br>"
				+ "관심분야 : " + member.memberInterest + "<br>"
				+ "가입일 : " + member.memberEnrollDate + "<br>"
			}else{
				result = "사용자 정보가 없습니다.";
			}
			$("#result-area").html(result);

		},error : function(){
			console.log("ajax 통신 실패");
		}
	});
});






