package com.kh.wsp.member.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.getSession().invalidate(); // session 무료화 -> 세션을 없앰
	    
	      // response.sendRedirect(request.getHeader("referer"));
	      // 로그아웃 요청 전 주소를 새롭게 요청함 
	    
	      response.sendRedirect(request.getContextPath());
	      // 로그아웃 시 메인페이지로 이동
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
