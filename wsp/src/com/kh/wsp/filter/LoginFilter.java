package com.kh.wsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.wsp.member.model.vo.Member;

@WebFilter(urlPatterns= {"/member/myPage.do"})
public class LoginFilter implements Filter {

    public LoginFilter() { }
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoginFilter 작동");

	} 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		// HttpServletRequest는 ServletRequest의 자식이므로 강제 형변환 가능
		
		HttpServletResponse res = (HttpServletResponse)response;
		// HttpServletResponse는 ServletResponse의 자식이므로 강제 형변환 가능
		
		// 현재 session을 얻어옴
		HttpSession session = req.getSession();
		
		Member member = (Member)session.getAttribute("loginMember");
		if(member == null) { // session에 loginMember 속성(키)에 대응되는 값이 없을 경우
							// == 로그인이 되어있지 않음
			session.setAttribute("status", "error");
			session.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			session.setAttribute("text", "로그인 후 이용해주세요.");
			res.sendRedirect(req.getContextPath()); // 메인페이지로 이동
		}else {
			// 로그인 되어있는 경우 다음 필터로 넘어가거나
			// 다음 필터가 없을 경우 요청된 servlet 또는 jsp로 이동
			chain.doFilter(request, response);
		}
	}

	public void destroy() {}
}
