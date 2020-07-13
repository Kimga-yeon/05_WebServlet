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

@WebServlet("/member/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 회원정보 수정에 필요한 파라미터, 세션 값 변수에 저장하기
		
		// 1) HttpSession에서 로그인 정보를 얻어옴.
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 2) 파라미터를 얻어옴
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
	
		String memberPhone = phone1 + "-" + phone2 + "-" + phone3;
		
		String memberEmail = request.getParameter("email");
		
		String post = request.getParameter("post");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		String memberAddress = post + "," + address1 + "," + address2;
		
		String[] interest = request.getParameterValues("memberInterest");
		String memberInterest = null;
		if(interest != null) {
			memberInterest = String.join(",", interest);
			
		}
		
		// 아이디, 전화번호, 이메일, 주소, 관심분야 
		Member member = new Member(loginMember.getMemberId(), memberPhone, memberEmail, memberAddress, memberInterest);
		
		try {
			int result = new MemberService().updateMember(member);
			
			String status = null;
			String msg = null;
			String text = null;
			if(result > 0) {
				status = "sucess";
				msg = "회원 정보 수정 성공";
				text = "회원 정보가 수정되었습니다.";
				
				
				// 회원 정보가 수정된 경우 DB뿐만 아니라 Sessiont도 갱신이 필요함.
				member.setMemberName(loginMember.getMemberName());
				member.setMemberGrade(loginMember.getMemberGrade());
				
				
				session.setAttribute("loginMember", member);
				
			}else {
				status = "error";
				msg = "회원 정보 수정 실패";
				text = "회원 정보 수정이 실패하였습니다.";
			}

			session.setAttribute("status", status);
			session.setAttribute("msg", msg);
			session.setAttribute("text", text);
			
			response.sendRedirect("myPage.do");

		}catch(Exception e) {
	         e.printStackTrace();
	         request.setAttribute("errorMsg", "회원 정보 수정 중 오류가 발생했습니다.");
	         
	         String path ="/WEB-INF/views/common/errorPage.jsp";
	         RequestDispatcher view = request.getRequestDispatcher(path);
	         view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
