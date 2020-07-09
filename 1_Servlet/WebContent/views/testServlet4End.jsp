<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   // 스크립틀릿 : JSP 페이지 내부에 자바 코드를 작성할 수 있는 영역
   
   // testServlet4.html 에서 입력된 파라미터 값 얻어오기
   String name = request.getParameter("name");
   String age = request.getParameter("age");
   String city = request.getParameter("city");
   String height = request.getParameter("height");
   String gender = request.getParameter("gender");
   
   // TestServlet.java 서블릿 파일에서 가공된 값 얻어오기
   // request에 Attribute로 세팅된 값의 자료형은 Object 타입으로 변하게됨.
   // --> 정상적으로 가공 데이터를 사용하고 싶은경우 알맞은 타입으로 형변환이 필요.
   String foodJoin = (String)request.getAttribute("foodJoin");
   String gift = (String)request.getAttribute("gift");
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 출력 화면(Servlet + JSP)</title>
<style>
    h2{color : red;}
    span.name{color: orange;}
    span.gender{color: yellow; background-color: black;}
    span.age{color:green;}
    span.city{color:blue;}
    span.height{color:navy;}
    span.food{color:purple;}
    span{font-weight: bold;}
</style>
</head>
<body>
<h2>개인 정보 입력 결과(Servlet + JSP)</h2>

      <!-- jsp 표현식(expression) -> 
         < %= %>
         화면상에 자바 코드의 내용을 표현(출력)하기 위해 사용.
       -->

        <span class="name"><%= name %></span> 님은
        <span class="age"><%= age %></span> 이시며,
        <span class="city"><%= city %></span>에 사는 키 
        <span class="height"><%= height %></span>cm인 
        <span class="gender"><%= gender %></span> 입니다.<br>
        좋아하는 음식은  <span class="food"><%= foodJoin %></span> 입니다.
        
        <h3><%= age %> 에게 맞는 선물 추천 </h3>
        '<%= gift %>' 선물은 어떠신가요?
</body>
</html>