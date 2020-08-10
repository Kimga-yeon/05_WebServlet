package com.gy.wsp.member.model.service;

import static com.gy.wsp.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import com.gy.wsp.member.model.dao.MemberDAO;
import com.gy.wsp.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao;
	public MemberService() throws FileNotFoundException , IOException{
		dao = new MemberDAO();
	}
	
	
	/** 로그인용 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {
		
		Connection conn = getConnection();
		
		Member loginMember = dao.loginMember(conn,member);
		
		conn.close();
		
		return loginMember;
	}


	/** 회원가입용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		System.out.println(member);
		Connection conn = getConnection();
		int result = dao.signUp(conn,member);
		
		if(result>0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		conn.close();
		
		return result;
	}


	/** 비밀번호 수정 Service
	 * @param member
	 * @param newPwd
	 * @return
	 */
	public int updatePwd2(Member member, String newPwd) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.checkpwd(conn,member);
		
		if(result > 0) {
			member.setMemberPwd(newPwd);
			
			result = dao.updatePwd(conn,member);
			
			if(result<0) conn.commit();
			else		conn.rollback();
		}else {
			result = -1;
			
		}
		
		conn.close();
		
		return result;
		
	}
}
