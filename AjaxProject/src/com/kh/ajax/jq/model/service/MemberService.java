package com.kh.ajax.jq.model.service;

import static com.kh.ajax.common.DBCP.getConnection;

import java.sql.Connection;

import com.kh.ajax.jq.model.dao.MemberDAO;
import com.kh.ajax.jq.model.vo.Member;


public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	/** 아이디 중복 체크 Service
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception {
		Connection conn = getConnection();

		int result = dao.idDupCheck(conn,id);
		
		conn.close();
	
		return result;
	}
	
	
	public Member selectMember(String id) throws Exception{
		Connection conn = getConnection();
		
		Member member = dao.selectMember(conn,id);
		conn.close();
		return member;
		
		
		
	}
}
