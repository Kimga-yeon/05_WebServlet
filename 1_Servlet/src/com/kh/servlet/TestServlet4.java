package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet4")
public class TestServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet4() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// POST 방식으로 전달된 데이터의 문자 인코딩을 UTF-8로 변경 

		// 요청 페이지에서 전달된 input태그의 값 == 파라미터(Parameter)
		// 파라미터는 모두 String 타입이다.
		
		// 요청 데이터 중 가공이 필요한 데이터 만을 얻어옴.
		String age = request.getParameter("age"); // 나이

		String[] foodArr = request.getParameterValues("food"); // 좋아하는 음식

		// check box의 체크 개수가 0개인 경우 null 처리 필요
		String foodJoin = null;
		if(foodArr != null) {
			foodJoin = String.join(", ", foodArr);
			// String.join(구분자,배열)
			// -> 배열의 각 요소를 하나의 문자열로 합치는 메소드
			//  	요소 사이마다 구분자를 추가함.

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
		
		// 기존에 작성되었던 응답 화면 작성부분을 JSP로 대체함
		// -> 분리된 Servlet과 JSP를 연결 해주는 작업이 필요
		// 요청 객체(HttpServletRequest)를 JSP로 위임!
		
		// 요청 위임 객체 : RequestDispatcher
		/*
		 * Dispatcher : 발송자, 필요한 정보를 제공하는 역할을 맡은 자
		 * 
		 * RequestDisPatcher : 
		 * 		현재 request에 담긴 정보를 저장하고 
		 * 		다음페이지(JSP)에서 해당 정보를 볼 수 있게 위임하는 역할 
		 * */
		
		RequestDispatcher view = request.getRequestDispatcher("views/testServlet4Emd.jsp");
		
	
	
	
	
	 // JSP로 요청을 위임하기 전에
    // 요청 객체에 포함되지 않은 새로운 정보(가공데이터 등)를 전달하는 방법
    // -> request.setParmeter() 같은 메소드는 존재하지 않음
    // -> request객체에 (K,V) 형태의 속성을 추가하여 전달 
	// void javax.servlet.ServletRequest.setAttribute(String key, String value)
	request.setAttribute("foodJoin",foodJoin);
	request.setAttribute("recommendation",recommendation);
	

	// jsp로 요청 객체 위임 
	view.forward(request, response);
	
	/*
	 * forword 방식 : 요청 정보를 유지한 상태로 페이지 전환 
	 * 				(servlet -> jsp)
	 * 
	 * forword 특징 : 화면이 jsp로 전환되도 요청 주소가 유지됨.
	 * 
	 * A.html 또는 A.jsp - (요청) -> Servlet - (요청 위임) -> B.jsp
	 * 
	 * A에서 전달받은 파라미터를 Servlet이 B로 위임함 
	 * 	--> B에서 A의 파라미터에 접근 가능 
	 * 
	 * */
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
