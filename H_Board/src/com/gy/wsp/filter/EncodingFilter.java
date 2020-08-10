package com.gy.wsp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// @WebFilter : 어떤 요청에 대해 필터가 적용될지 작성하는 어노테이션
// - FilterName : 필터의 이름을 지정(필터가 동작할 순서를 지정할 때 사용)
// - urlPatterns : 필터가 적용될 요청 주소 패턴 지정 ( /* : 현재 프로젝트 모든 url)
@WebFilter(filterName="encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter {
	
    public EncodingFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청/응답 시 문자 인코딩을 UTF-8로 변환하는 필터 작성
		
		// 1. 요청 데이터 문자 인코딩 변경
		request.setCharacterEncoding("UTF-8");
		

		// 2. 응답 데이터 mine type, 문자 인코딩 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		/* Filter.doFilter() : 요청/ 응답에 대한 필터링 작업
		 * FilterChain.doFilter() : 다음 필터 호출 (마지막 필터일 경우 servlet/jsp 으로 전달)
		 * 
		 * */
	}

	public void destroy() {
	}

}
