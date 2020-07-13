package com.kh.wsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.wsp.member.model.service.MemberService;
import com.kh.wsp.member.model.vo.Member;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/member/signUp.do")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. POST 방식으로 전달받은 데이터 문자 인코딩 변경
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전송값(파라미터)를 모두 변수에 저장
		String memberId = request.getParameter("id");
		String memberPwd = request.getParameter("pwd1");
		String memberName = request.getParameter("name");

		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		
		// 전화번호를 구분자 '-' 를 이용하여 합침 
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		
		String memberEmail = request.getParameter("email");
		
		String post = request.getParameter("post");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		// 주소를 ","를 구분자로 하여 합치기
		String memberAddress = post + "," + address1 + "," + address2;
		
		// 관심분야가 하나도 선택이 되지 않았을 경우를 대비해 null처리
		String[] interest = request.getParameterValues("memberInterest");
		String memberInterest = null;
		if(interest != null) {
			memberInterest = String.join(",", interest);
		}
		
		// 오버로딩 : 매개변수의 수, 타입, 순서 중 하나라도 다르면 성립 
		
		// -> 비밀번호를 제외한 매개변수 6개 짜리 생성자를 생성
		// -> 생성자를 이용해 값 6개 세팅 후 setter를 이용해 비밀번호도 세팅
		Member member = new Member(memberId, memberName, phone, memberEmail, memberAddress, memberInterest);
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
				text= "회원 가입 중 문제가 발생했습니다. 문제가 지속될 경우 문의바람";
				
			}
			
			// 세션을 이용한 메세지 임시 전달 
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("text", text);

			// 회원 가입 성공 또는 실패 시 메일 페이지 주소로 돌아가도록 요청
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
