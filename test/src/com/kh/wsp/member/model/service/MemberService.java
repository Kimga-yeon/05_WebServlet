package com.kh.wsp.member.model.service;

import static com.kh.wsp.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.kh.wsp.member.model.dao.MemberDAO;
import com.kh.wsp.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao;
	public MemberService() throws FileNotFoundException, IOException{
		dao = new MemberDAO();
	}
	
	public Member loginMember(Member member) throws Exception {
		Connection conn = getConnection();
		Member loginMember = dao.loginMember(conn,member);
		conn.close();
		return loginMember;
	}


	

}
