package com.kh.ajax.jq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kh.ajax.jq.model.vo.User;

@WebServlet("/jqAjax3.do")
public class JqAjaxServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 샘플데이터를 저장할 List 선언 
		List<User> list = new ArrayList<>();
		
	    list.add(new User(1,"박철수", 30, '남'));
	    list.add(new User(2,"김영희", 26, '여'));
	    list.add(new User(3,"오영심", 32, '여'));
	    list.add(new User(4,"이민기", 28, '남'));
	    list.add(new User(5,"홍길동", 33, '남'));
	    
	    // 요청 시 전달받은 번호 저장
	    int input = Integer.parseInt(request.getParameter("input")) -1 ;
	    
	    // JSON(JavaScript Object Nation) : 자바 스크립트 객체 표현법 
	    // {"K" : V, "K" : V , "K" : V , ... }
	    // 경량 데이터 교환 방식으로 순수 TEXT(문자열)임.
	    // Ajax 비동기 통신으로 Object를 전달할 경우
	    // Object를 직접 전달하는 것이 아닌 JSON의 형태로 전달함.
	    
	    // String jsonUser = null;
	    JSONObject jsonUser = new JSONObject();
	    
	    
	   /* jsonUser = "{\"no\":1,"
	    		+ "\"name\" : \"박철수\","
	    		+ "\"age\" : 30,"
	    		+ "\"gender\":\"남\"}";*/
	    
	    if(input >= 0 && input < list.size()) {
	        /* jsonUser=   "{\"no\":" + list.get(input).getNo()
	                  + ",\"name\":\"" + list.get(input).getName() + "\""
	                  + ",\"age\":"  + list.get(input).getAge()
	                  + ",\"gender\":\"" + list.get(input).getGender() + "\"}"; */
	    	
	    	// 자바에서 JSON을 사용하기 위한 라이브러리(json-simple-1.1.1)
	    	// https://code.google.com/archive/p/json-simple/downloads
	    
	    	// JSONObject : JSON 형태로 데이터를 핸들링 할 수 있는 객체
	    	// -> Map 형식의 객체로 데이터를 ("K" : V ) 형태로 다룸.
	    	jsonUser.put("no", list.get(input).getNo());
	    	jsonUser.put("name", list.get(input).getName());
	    	jsonUser.put("age", list.get(input).getAge());
	    	jsonUser.put("gender", list.get(input).getGender()+"");
	    	
	    	// JSONObject에 추가된 데이터를
	    	// 자바스크립트 객체 형태로 변환된 문자열을 반환
	    	System.out.println(jsonUser.toJSONString());
	    	System.out.println(jsonUser.toString());
	    	System.out.println(jsonUser);
	    			
	    }
	    
	    response.setCharacterEncoding("UTF-8");
	    
	    // 응답되는 데이터가 JSON 데이터임을 인식 시키는 방법1
	    // - Mime - Type을 이용하여 JSON으로 인식 시키기
	    //response.setContentType("application/json; charset=UTF-8");
	    response.getWriter().print(jsonUser);
	    
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
