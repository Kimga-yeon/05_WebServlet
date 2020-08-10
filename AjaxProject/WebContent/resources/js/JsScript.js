// XMLHTTPRequest 객체를 저장할 변수 선언 
var xhr;

// XMLHttpRequest 객체 생성 함수 작성
// -> 크로스 브라우저 대처 작업을 진행하여 객체 생성 
// 	 --> 브라우저별로 Ajax에 사용되는 객체가 다르기 때문 
function getXMLHttpRequest(){
	
	// IE7버전 이상 & 나머지 모든 브라우저
	if(window.XMLHttpRequest){
		// 현재 브라우저에 XMLHttpRequest 있을 경우
		return new XMLHttpRequest();
		
	}
	else if(window.ActiveXObject){ // IE7버전 미만
		try{
			return new ActiveXObject("Microsoft.XMLHTTP");
			
		}catch(e){
			return null;
		}
		
	}
	else{ // Ajax를 지원하지 않는 브라우저 
		return null;
	}
}

var jsBtn1 = document.getElementById("jsBtn1");

// 1. GET 방식으로 서버에 데이터 전송 및 응답
jsBtn1.addEventListener("click", function(){
	
	// 1) XMLHttpRequest객체 생성 
	xhr = getXMLHttpRequest();
	
	// 2) onreadystatechange 함수 작성 
	// -> Ajax 통신이 성공한 경우에 대한 동작 작성 
	xhr.onreadystatechange = function(){

		// 2-1) readyState : 서버 응답 상태 확인 
		if(xhr.readyState == 4){
			// 요청을 정상적으로 받고 응답할 준비가 되어있음.

			// 2-2) status : HTTP 응답 상태 코드 
			// 응답이 정상적으로 이루어 졌는지 확인하는 코드 
			if(xhr.status == 200){
				console.log("ajax통신 성공");

				// responseText : 응답데이터 문자열 반환
			var str = xhr.responseText;

			document.getElementById("result-area").innerHTML=str;
			}
		}

	}

	// 3) open() : 서버와 데이터 교환 시 필요한 정보를 입력 
	xhr.open("GET", "jsAjax1.do?name=홍길동&age=20",true);

	// 4) send() : 서버로 데이터 교환 요청 
	xhr.send();

});

var jsBtn2 = document.getElementById("jsBtn2");

// 2. POST 방식으로 서버에 데이터 전송 및 응답 
jsBtn2.addEventListener("click",function(){
  
	 // 1) XMLHttpRequest 객체 생성
	 xhr = getXMLHttpRequest();

	 // 2) onreadystatechange
	 xhr.onreadystatechange = function(){
         if(xhr.readyState == 4 && xhr.status == 200){
			 var str = xhr.responseText;

			 document.getElementById("result-area").innerHTML = str;
		 }
	 }

	 // 3) open() : 서버와 데이터 교환 시 필요한 정보 입력
	 xhr.open("POST", "jsAjax2.do",true);
	 
	 // ** post 방식으로 데이터 전송 시 send() 호출 전에
	 // Mime type을 설정해 주어야 함.
	 xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	 // -> 미작성 시 전송한 파라미터를 서버가 인식할 수 없음.


	 // 4) send()
	 xhr.send("name=신사임당&age=40");
	 

});