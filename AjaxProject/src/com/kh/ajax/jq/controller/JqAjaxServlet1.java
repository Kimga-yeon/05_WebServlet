package com.kh.ajax.jq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jqAjax1.do")
public class JqAjaxServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// data 속성에 작성되어있던 파라미터 얻어오기
		String input = request.getParameter("input");
	
		// 응답 데이터 문자 인코딩 변경
		response.setCharacterEncoding("UTF-8");
		
		// 응답을 위한 스트림 연결 및 데이터 전송 
		response.getWriter().print("입력된 값 : " + input + ", 길이 : " + input.length());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
