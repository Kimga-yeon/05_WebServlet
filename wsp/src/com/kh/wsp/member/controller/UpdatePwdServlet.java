package com.kh.wsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.wsp.member.model.service.MemberService;
import com.kh.wsp.member.model.vo.Member;

@WebServlet("/member/updatePwd.do")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 입력된 파라미터(현재, 새 비밀번호)를 얻어옴.
		String currentPwd = request.getParameter("currentPwd");
		String newPwd = request.getParameter("newPwd1");
		
		// session에 있는 loginMember에서 아이디를 얻어옴.
		HttpSession session = request.getSession();
		String memberId = ( (Member)session.getAttribute("loginMember")).getMemberId();
		
		Member member = new Member(memberId, currentPwd);
		
		try {
			//int result = new MemberService().updatePwd(member, newPwd);
			
			
			int result = new MemberService().updatePwd2(member, newPwd);
			
			String status = null;
			String msg = null;
			if(result > 0) { // 비밀번호 변경 성공
				status = "success";
				msg = "비밀번호가 변경 되었습니다";
			}else if(result == 0) { // 비밀번호 변경 실패
				status = "error";
				msg = "비밀번호 변경에 실패하였습니다";
			}else { // 현재 비밀번호 불일치 
				status = "error";
				msg = "현재 비밀번호가 일치하지 않습니다";
			}
			
			session.setAttribute("status", status);
			session.setAttribute("msg", msg);
			response.sendRedirect("changePwd.do");
			
		}catch (Exception e){
			e.printStackTrace();
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			
			request.setAttribute("errorMsg", "비밀번호 수정 과정에서 오류 발생");
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
