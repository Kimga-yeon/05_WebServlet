package com.kh.wsp.member.model.dao;

import java.beans.Statement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Properties;

import com.kh.wsp.member.model.vo.Member;

public class MemberDAO {

	private Properties prop = null;

	public MemberDAO() throws FileNotFoundException, IOException {
		String fileName = MemberDAO.class.getResource("/com/kh/wsp/sql/member/member-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}


	/** 로그인용 DAO
	 * @param conn
	 * @param member
	 * @return
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginMember = null;

		String query = prop.getProperty("loginMember");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());

			rset = pstmt.executeQuery();

			if(rset.next()) {
				loginMember = new Member(
						rset.getString("MEMBER_ID"),
						rset.getString("MEMBER_NM"),
						rset.getString("MEMBER_PHONE"),
						rset.getString("MEMBER_EMAIL"),
						rset.getString("MEMBER_ADDR"),
						rset.getString("MEMBER_INTEREST"),
						rset.getString("MEMBER_GRADE")

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
	 * @return Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;   //DML은 반환할 행의 갯수 INT가 필요하다
		
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setString(7, member.getMemberInterest());
			
			
			result = pstmt.executeUpdate();
					
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}


	/** 아이디 중복 체크용 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id)throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		
		String query = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			
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


	/** 회원 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0; 
		
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberAddress());
			pstmt.setString(4, member.getMemberInterest());
			pstmt.setString(5, member.getMemberId());
			
			
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
	public int checkPwd(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
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
	 * @throws Exception
	 */
	
	public int updatePwd(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result;
	}

	public int updatePwd(Connection conn, Member member, String newPwd) throws Exception {
		
		// CallableStatement : 
		// 데이터베이스 내의 저장된 프로시저를 호출하기 위해 존재하는 객체
		
		CallableStatement cstmt = null;
		int result = 0;
		
		String query = "{call UPDATE_PWD(?, ?, ?, ?)}";
		
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, member.getMemberId());
			cstmt.setString(2, member.getMemberPwd());
			cstmt.setString(3, newPwd);
			cstmt.registerOutParameter(4, Types.NUMERIC);
			
			cstmt.execute();
			result = cstmt.getInt(4);
			
		}finally {
			cstmt.close();
		}
		return result;
	}


	/** 회원 정보 조회 DAO
	 * @param conn
	 * @param memberId
	 * @return selectMember
	 * @throws Exception
	 */
	public Member selectMember(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member selectMember = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				selectMember = new Member(rset.getString("MEMBER_ID"), 
										rset.getString("MEMBER_NM"),
										rset.getString("MEMBER_EMAIL"), 
										rset.getString("MEMBER_INTEREST"), 
										rset.getDate("MEMBER_ENROLL_DATE")
						);
												  
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return selectMember;
		
	}

}
