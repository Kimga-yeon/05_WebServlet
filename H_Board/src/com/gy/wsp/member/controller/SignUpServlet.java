package com.gy.wsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gy.wsp.member.model.service.MemberService;
import com.gy.wsp.member.model.vo.Member;

@WebServlet("/member/signUp.do")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberEmail = request.getParameter("email");
		String memberName = request.getParameter("name");
		String memberPwd = request.getParameter("pwd1");
		
		String memberNick = request.getParameter("nick");
		String memberGender = request.getParameter("memberGender");
		
		Member member = new Member(memberEmail, memberName, memberNick, memberGender);
		member.setMemberPwd(memberPwd);
		
		try {
			int result = new MemberService().signUp(member);
			
			String status = null;
			String msg = null;
			String text = null;
			
			if(result > 0) {
				status = "success";
				msg = "회원가입 성공!";
				text = "회원가입을 환영합니다.";
			}else {
				status = "error";
				msg = "회원가입 실패!";
				text = "회원 가입 중 문제가 발생했습니다. 문제가 지속될 경우 문의바람";
			}
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);
			
			response.sendRedirect(request.getContextPath());
			
		}catch(Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "회원 가입 중 오류 발생");
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
