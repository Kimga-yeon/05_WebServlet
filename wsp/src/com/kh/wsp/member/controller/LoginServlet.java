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

// @WebServlet 어노테이션
// 해당 클래스를 Servlet으로 등록하고, 
// 매개변수로 작성된 url패턴의 요청을 매핑하도록 지정함.
// -> web.xml에 작성하던 Servlet 등록, 매핑 작업을 단축 시킴 

@WebServlet("/member/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. POST 방식으로 전달된 값의 문자 인코딩 처리 
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전달 받은 값을 꺼내 변수에 저장
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");

		// 3. Member 객체를 생성하여 id, pwd를 저장하여 한 번에 전달 할 수 있게함
		Member member = new Member(memberId, memberPwd); 
	
		
		try {
			// 4. 비즈니스 로직 처리를 하는 Service메소드 호출하고
			// 반환값을 저장함.
			Member loginMember = new MemberService().loginMember(member);
			
			
			HttpSession session = request.getSession();
			
			
			// 요청 성공
			if(loginMember != null) {
				// Session : 서버쪽에 요청이 들어온 브라우저 마다 하나씩 생성되며
				//			 브라우저가 종료 되거나, 세션이 만료 될 때까지 
				// 			 사라지지 않고 유지되면, 어떤 페이지에서든 접근 가능함.
				// 			 (Session은 Server에서 관리가 됨.)
				
				//세션에 "loginMember"키 값으로 로그인된 정보를 속성으로 추가함.
				session.setAttribute("loginMember", loginMember);
				
				// 초 단위로 세션이 유지되는 시간을 지정
				session.setMaxInactiveInterval(1800); // 3000초
				
				/*
				 * Cookie란 클라이언트 측 브라우저에 저장되는 
				 * 5KB 이하의 문자열을 저장하는 파일로 
				 * [K, V]형태로 되어 있으며
				 * 주로 아이디 저장, Session ID가 저장된다.
				 * 
				 * */

				// **** Cookie 생성 **** 
				// 1. 로그인 성공 시 "아이디 저장" 체크 박스가 체크되었는지 따라 쿠키를 생성한다.
				
				String saveId = request.getParameter("saveId");
				Cookie cookie = new Cookie("saveId",memberId);
				
				if(saveId != null) { // 아이디 저장이 체크가 된 경우
					// 2. 쿠키가 유지될 수 있는 유효기간 설정(초단위)
					cookie.setMaxAge(60* 60 * 24 * 7); // 유효기간 7일 설정 
				}else {
					cookie.setMaxAge(0); // 0초인 경우 쿠키가 삭제됨
				}
				
				// 3. 쿠키가 사용될 수 있는 유효한 디렉토리(주소)를 설정 
				cookie.setPath("/"); // 최상위 디렉토리(주소)를 나타냄
									 // == 어떤 페이지 에서도 사용 가능 
				
				// 4. response객체에 쿠키 객체를 담아 응답
				response.addCookie(cookie);
				
			
			}else {  // 요청 실패
				String msg = "로그인 실패!";
				String status = "error";
				String text = "아이디 또는 비밀번호를 확인 해주세요.";
				
				// 리다이렉트 방식은 이전 요청 객체 rquest를 폐기하므로
				// 다음 페이지에 메세지를 전달하고 싶을 때 Session을 임시로 사용하는 방법이 있다.
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("text", text);
						
			}
			// foaward 방식 
			// 지정된 주소에 위치한 프레젠테이션 로직이 구현된 JSP로 
			// 기존 요청 객체를 위임하는 방식.
			// 서블릿 컨테이너의 입장에서는
			// 요청이 Servlet -> JSP로 이동(위임)한 모습으로
			// 페이지를 전환하는 것 처럼 보이지만
			
			// 클라이언트 입장에서는 하나의 요청이 처리되는 것일 뿐임.
			// --> 주소창에 표시된 주소가 요청 주소로 유지가됨.
			// 	   (주소가 JSP 경로로 변화하지 않음) 
			
			
			// redirect 방식 
			// 기존 요청 시 request, response 객체를 폐기하고(이전 요청 내용이 사라짐)
			// 새로 작성된 주소로 새로운 요청을 보냄 
			// --> 새로운 요청으로 인해 주소창 주소가 바뀜
			//					(기존 요청 -> 신규 작성 요청)
			
			response.sendRedirect(request.getHeader("referer"));
			
			// referer : 사이트 방문 흔적
			// request.getHeader("referer") : 요청 이전 페이지 주소가 반환됨.
			
			
			 // RequestDispatcher view = request.getRequestDispatcher("/");
			 // view.forward(request, response);
			
			
			
		}catch(Exception e) { //요청 처리 중 오류가 발견했을 때 
			e.printStackTrace();
			
			// 로그인 과정 중 오류 발생 시 
			// "로그인 과정에서 문제가 발생했습니다." 라는 문구를 출력해줄 페이지 요청 
			// --> 공용 에러페이지 작성 후 메세지만 전달 
			
			request.setAttribute("errorMsg", "로그인 과정에서 문제가 발생했습니다.");
			
			// 요청 위임 객체 준비 
			RequestDispatcher view
				= request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
