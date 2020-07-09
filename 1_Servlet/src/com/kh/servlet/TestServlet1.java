package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet1() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 메소드 호출!");
		// 클라이언트의 요청을 받은 서블릿이 메모리에 적재가 되어있지 않다면
		// init() 메소드가 실행되면서 해당 서블릿이 메모리에 적재됨.
		
		// init() 메소드가 오버라이딩 되어있지 않다면
		// 메모리 적재 후 service() 메소드를 호출함
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 메소드 호출");
		// 요청 방식이 get인지 post인지 판별하여
		// doGet() 또는 doPost()를 호출하는 역할
		
		doPost(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송 방식(Get / Post) 관계 없이 동일한 코드 동작
		
		// HttpServletRequest request
		// -> 클라이언트의 요청 정보, 입력값 등을 전달 받아 오는 객체
		
		// HttpServletResponse response
		// -> 첫 번째 매개변수인 request의 처리 결과(응답) 클라이언트에게 내보내거나
		//    내보내기 위한 필요 정보들이 담겨있는 객체
		
		// 1. 입력한 데이터를 얻어오기
		String name = request.getParameter("name");	// 이름
		// 전달 받은 input 태그 값 중 name 속성의 값이 "name"인 
		// input태그의 값을 얻어와 String name 변수에 저장;

		String gender = request.getParameter("gender");
		// input 태그 중 name 속성값이 gender인 태그는 radio button 이지만
		// 여러 radio button 중 한 개만 선택이 가능함. == 값이 하나만 전달됨.
		
		String age = request.getParameter("age"); // 나이
		
		String city = request.getParameter("city"); // 사는 도시
		
		String height = request.getParameter("height"); // 키
		// input 태그의 value 값은 무조건 String 이므로
		// 만약 숫자 연산이 필요할 경우 parsing을 진행하여 수행할 것.
		// String -> int  :  Integer.parseInt("String값");
		
		// 체크박스 같은 하나의 name속성으로 여러 값 얻어오기
		String[] foodArr = request.getParameterValues("food");
		// request.getParameterValues("name속성값")
		// 요청 데이터 중 같은 name 속성값을 가지고 있는 input 태그의 value를
		// String 배열 형태로 저장하여 반환함.
		
		
		System.out.println("name : " + name);	
		System.out.println("gender : " + gender);	
		System.out.println("age : " + age);	
		System.out.println("city : " + city);	
		System.out.println("height : " + height);	
		
		System.out.print("좋아하는 음식 : ");
		if(foodArr != null) {
			for(String food : foodArr) {
				System.out.print(food + " ");
			} 
			System.out.println();
		}else {
			System.out.println("없음");
		}
		
		
		
		// 데이터를 가공하여 응답화면을 만들어 클라이언트에게 전달
		
		// 응답 화면의 mime type 지정 및 문자 인코딩 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// HTML 코드를 출력할 수 있는 스트림 연결
		// -> HttpServletResponse 객체에서 
		//    응답할 클라이언트와 연결할 수 있는 스트림을 얻어와 연결
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html lang='ko'>");
		out.print("<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"        <title>개인 정보 출력 화면(GET)</title>\r\n" + 
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
				"        <h2>개인 정보 입력 결과(GET)</h2>\r\n" + 
				"        <span class=\"name\">" + name + "</span> 님은\r\n" + 
				"        <span class=\"age\">" + age + "</span> 이시며,\r\n" + 
				"        <span class=\"city\">" + city + "</span>에 사는 키\r\n" + 
				"        <span class=\"height\">" + height + "</span>cm인\r\n" + 
				"        <span class=\"gender\">" + gender + "</span> 입니다.<br>\r\n" + 
				"        좋아하는 음식은 <span class=\"food\">" + String.join(", ", foodArr) + "</span>입니다.\r\n" + 
				"    </body>\r\n" + 
				"</html>");
		
		
		
	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	
	public void destroy() {
		System.out.println("destroy() 호출");
	}

}
