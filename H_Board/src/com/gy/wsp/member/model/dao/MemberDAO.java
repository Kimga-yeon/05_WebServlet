package com.gy.wsp.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.gy.wsp.member.model.vo.Member;

public class MemberDAO {

	private Properties prop = null;
	
	public MemberDAO() throws FileNotFoundException , IOException{
		String fileName = MemberDAO.class.getResource("/com/gy/wsp/sql/member/member-query.properties").getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
	}

	
	
	/** 로그인용 DAO
	 * @param conn 
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginMember = null;
		
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPwd());
		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMember = new Member(
						rset.getString("MEMBER_EMAIL"),
						rset.getString("MEMBER_NAME"),
						rset.getString("MEMBER_NICK"),
						rset.getString("MEMBER_GENDER")
						);
			}
		
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return loginMember;
	}



	/** 회원가입용 DAO
	 * @param conn
	 * @param member
	 * @return member
	 * @throws Exception
	 */
	
	public int signUp(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberNick());
			pstmt.setString(4, member.getMemberPwd());
			pstmt.setString(5, member.getMemberGender());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}



	/** 비밀번호 일치여부 확인 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int checkpwd(Connection conn, Member member) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return result;
	}



	/** 비밀번호 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 */
	public int updatePwd(Connection conn, Member member) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberEmail());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}

		return result;
		
	}
	
}
