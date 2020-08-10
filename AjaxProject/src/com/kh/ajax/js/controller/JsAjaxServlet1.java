package com.kh.ajax.js.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/jsAjax1.do")
public class JsAjaxServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 응답데이터 가공
		String str = name + "님의 나이는 " + age + "세 입니다.";
		
		// 응답 데이터 문자 인코딩 처리 
		// -> 응답화면에 맞는 문자 인코딩 처리가 필요함
		response.setCharacterEncoding("UTF-8");
		
		// 응답 데이터 전송 스트림
		PrintWriter out = response.getWriter();
		
		out.append("서버에서 전송한 값<br>");
		out.append(str);
	
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
