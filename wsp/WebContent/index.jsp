<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <style>
      .bg-image-full {
        background: no-repeat center center scroll;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        background-size: cover;
        -o-background-size: cover;
      }

      div.bg-image-full{
        height: 300px;
        text-align: center;
      }

      @font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }
      div.bg-image-full>h1{
        color : white;
        position: relative;
        top : 75px;
        font-size: 3em;
        font-family: 'GmarketSansBold';
        text-shadow: -2px 0 black, 0 2px black, 2px 0 black, 0 -2px black;
      }
  </style>
</head>
<body>
	<%@ include file="WEB-INF/views/common/header.jsp" %>

	<!-- 메인 화면 이미지 -->
	<div class="py-5 bg-image-full" style="background-image: url('https://iei.or.kr/resources/images/intro/intro_bg.jpg');">
	    <h1>Sevlet/JSP를 이용한 Web Application</h1>
	</div>
	
	<!-- 내용 작성 부분 -->
	<div class="py-5">
	  <div class="container">
	    <h1>Section Heading</h1>s
	    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
	    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, suscipit, rerum quos facilis repellat architecto commodi officia atque nemo facere eum non illo voluptatem quae delectus odit vel itaque amet.</p>
	  </div>
	</div>
	
	<div class="py-5">
	  <div class="container">
	     
	    <h1>Section Heading</h1>
	    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
	    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, suscipit, rerum quos facilis repellat architecto commodi officia atque nemo facere eum non illo voluptatem quae delectus odit vel itaque amet.</p>
	  </div>
	</div>
	<%@ include file="WEB-INF/views/common/footer.jsp" %>
	
</body>
</html>