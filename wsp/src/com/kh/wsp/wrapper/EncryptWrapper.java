package com.kh.wsp.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{

	// 상속받은 HttpServletRequestWrapper클래스는
	// 기본 생성자가 존재하지 않아 
	// 반드시 명시적으로 매개변수로 HttpServletRequest를 가지는
	// 생성자를 작성해 주어야 함.
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	// ServletRequestWrapper 의 getParameter() 메소드를 오버라이딩
	@Override
	public String getParameter(String name) {
		String encPwd = null; // 암호화된 비밀번호를 저장할 변수 
		
		// 요청 파라미터 중 비밀번호와 관련된 파라미터만을 검출 
		switch(name){
		case "memberPwd" : case "pwd1" : 
		case "currentPwd" : case "newPwd1" : 
			
			encPwd = getSha512(super.getParameter(name)); break;
			
		default : 
			encPwd = super.getParameter(name);
			// 전달받은 파라미터 그대로 반환
		}
		
		return encPwd;
	}
	
	/**	SHA-512 해시함수를 이용한 암호화 메소드
	 * @param memberPwd
	 * @return encPwd
	 */
	
	// 해시함수 : 입력된 값을 여러 단계의 연산을 거쳐서
	// 			  일정한 길이의 무작위 값을 얻어내는 함수
	public static String getSha512(String memberPwd) {
		
		// MessageDigest : 지정된 알고리즘에 따라 해시 함수를 수행하는 객체
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 암호화 수행 전 
		// 암호화 하려는 문자열(비밀번호)를 바이트 배열로 변환 
		byte[] bytes = memberPwd.getBytes(Charset.forName("UTF-8"));
		
		
		// 암호화 진행 
		// md 객체에 바이트 배열로 변환 된 비밀번호를 전달
		// -> 지정된 해시함수(SHA-512)방식으로 변환 진행
		md.update(bytes);

		// 암호화된 비밀번호 반환 
		String encPwd = Base64.getEncoder().encodeToString(md.digest());
		// java.util.Base64 인코더를 이용해 암호화 된 
		// 바이트 배열을 문자열로 변환
		
		System.out.println("암호화 전 : "+memberPwd);
		System.out.println("암호화 후 : "+encPwd);
		
		return encPwd;
		
	}
	
	
	
}
