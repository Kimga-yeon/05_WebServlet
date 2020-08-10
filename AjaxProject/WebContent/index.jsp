<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Ajax 연습하기</title>
<style>
	#result-area{
		width: 100%;
		height : 400px;
		border : 1px solid black;
	}
	
	#side{
		position : fixed;
		right : 0;
	}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="row mt-5">
		<div class="col-md-8">
			<div class="list-group">
			
				<h3 class="list-group-item list-group-item-action active">
					Javascript 방식의 Ajax
				</h3>
				
				<!-- 1. GET 방식으로 서버에 데이터 전송 및 응답 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-primary">1. 버튼 클릭 시 GET 방식으로 서버에 데이터 전송 및 응답</span>
						<button id="jsBtn1" class="btn btn-primary float-right">GET 방식 전송</button>
					</h5>
				</div>
				
				<!-- 2. POST 방식으로 서버에 데이터 전송 및 응답 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-primary">2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답</span>
						<button id="jsBtn2" class="btn btn-primary float-right">POST 방식 전송</button>
					</h5>
				</div>
				
				<h3 class="list-group-item list-group-item-action active bg-warning border-warning mt-3">
					jQuery 방식의 Ajax
				</h3>
				
				<!-- 1. 버튼 클릭 시  GET GET으로 서버에 데이터 전송 및 응답 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-warning">1. 버튼 클릭 시  GET 방식으로 서버에 데이터 전송 및 응답</span><br><br>
						입력 : <input type="text" id="input1">
						<button id="jqBtn1" class="btn btn-warning float-right">GET 방식 전송</button>
					</h5>
				</div>
				
				<!-- 2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-warning">2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답</span><br><br>
						입력 : <input type="text" id="input2">
						<button id="jqBtn2" class="btn btn-warning float-right">POST 방식 전송</button>
					</h5>
				</div>
				
				<!-- 실시간 아이디 중복 검사 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-warning">실시간 아이디 중복 검사</span><br><br>
						입력 : <input type="text" id="inputId">
						<span id="idDupResult"></span>
					</h5>
				</div>
				
				<!-- 3. [JSON]서버로 기본형 데이터 전송 후, 응답을 객체(Obejct)로 받기 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-warning">3. [JSON]서버로 기본형 데이터 전송 후, 응답을 객체(Obejct)로 받기</span><br>
						1 ~ 5 사이 입력 : <input type="text" id="input3">
						<button id="jqBtn3" class="btn btn-warning float-right">전송</button>
					</h5>
				</div>
				
				
				<!-- [JSON] 실시간으로 입력받은 아이디와 일치하는 회원이 존재하면 회원 정보 조회하기 -->
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<span class="text-warning">입력받은 아이디와 일치하는 회원이 존재하면 회원 정보 조회하기</span><br>
						ID 입력 : <input type="text" id="inputId2">
						<button id="selectMemberBtn" class="btn btn-warning float-right">조회</button>
					</h5>
				</div>
				
				
				
				
			</div>
		</div>
		
		
		
		<div class="col-md-4" id="side">
			<div class="list-group" >
				<h3 class="list-group-item list-group-item-action active bg-secondary border-secondary">
					Ajax 수행 결과
				</h3>
				
				<div id="result-area">
					
				</div>
				
			</div>
		</div>
	</div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="resources/js/jsScript.js"></script>
<script src="resources/js/jQueryScript.js"></script>
</body>
</html>