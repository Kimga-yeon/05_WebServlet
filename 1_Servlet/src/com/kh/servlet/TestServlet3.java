package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 실습
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 데이터 문자 인코딩 변경 
		request.setCharacterEncoding("UTF-8");
		
		// 1. 입력한 데이터를 얻어오기
		String name = request.getParameter("name");	// 이름
		String gender = request.getParameter("gender"); // 성별
		String age = request.getParameter("age"); // 나이
		String city = request.getParameter("city"); // 사는 도시
		String height = request.getParameter("height"); // 키
		String[] foodArr = request.getParameterValues("food"); // 좋아하는 음식
		
		// 좋아하는 음식을 문자열로 변경
		String foodJoin = null;
		if(foodArr != null) {
			foodJoin = String.join(", ", foodArr);
		}else {
			foodJoin = "없음";
		}
		
		//나이 기준으로 선물 추천
		//recommendation : 추천
		String recommendation = "";
		switch(age) {
		case "10대 미만": recommendation = "장난감"; break;
		case "10대": recommendation = "기프티콘"; break;
		case "20대": recommendation = "현금"; break;
		case "30대": recommendation = "항공권"; break;
		case "40대": recommendation = "명품"; break;
		case "50대": recommendation = "안마의자"; break;
		}
		
		
		
		// 응답 화면의 mime type 지정 및 문자 인코딩 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// 응답할 클라이언트와 연결할 수 있는 스트림을 얻어와 연결
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html lang='ko'>");
		out.print("<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"        <title>개인 정보 출력 화면(비즈니스 로직)</title>\r\n" + 
				"        <style>\r\n" + 
				"            h2{ color : red;}\r\n" + 
				"            span.name{ color : orange;}\r\n" + 
				"            span.gender{ color: yellow; background-color: black;}\r\n" + 
				"            span.age{ color : green;}\r\n" + 
				"            span.city{ color : blue;}\r\n" + 
				"            span.height{ color : navy;}\r\n" + 
				"            span.food{ color : purple;}\r\n" + 
				"            span{font-weight: bold;}\r\n" + 
				"        </style>\r\n" + 
				"    </head>\r\n" + 
				"    <body>\r\n" + 
				"        <h2>개인 정보 입력 결과(비즈니스 로직)</h2>\r\n" + 
				"        <span class=\"name\">" + name + "</span> 님은\r\n" + 
				"        <span class=\"age\">" + age + "</span> 이시며,\r\n" + 
				"        <span class=\"city\">" + city + "</span>에 사는 키\r\n" + 
				"        <span class=\"height\">" + height + "</span>cm인\r\n" + 
				"        <span class=\"gender\">" + gender + "</span> 입니다.<br>\r\n" + 
				"        좋아하는 음식은 <span class=\"food\">" + foodJoin + "</span>입니다.\r\n" + 
			"			 <h3>" + age + "에 맞는 선물 추천</h3>" +
				"		 '" + recommendation + "' 선물은 어떠신가요?" +
				"    </body>\r\n" + 
				"</html>");
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	
	
}
