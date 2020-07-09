package com.kh.wsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.wsp.member.model.service.MemberService;
import com.kh.wsp.member.model.vo.Member;

@WebServlet("/member/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		Member member = new Member(memberId, memberPwd);
		
		try {
			Member loginMember = new MemberService().loginMember(member);
			HttpSession session = request.getSession();
			
			if(loginMember != null) {
				session.setAttribute("loginMember", loginMember);
				session.setMaxInactiveInterval(1800);
			
				String saveId = request.getParameter("saveId");
				Cookie cookie = new Cookie("saveId",memberId);
		
				if(saveId != null) {
					cookie.setMaxAge(60*60*24*7);
				}else {
					cookie.setMaxAge(0);
				}
				
				cookie.setPath("/");
				response.addCookie(cookie);
			
			}else {
				String msg = "로그인 실패!";
				String status = "error";
				String text = "아이디 또는 비밀번호를 확인 해주세요.";
				
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("text", text);
			}
			response.sendRedirect(request.getHeader("referer"));
			
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "로그인 과정에서 문제 발생");
			
			RequestDispatcher view
				= request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
