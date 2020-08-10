package com.kh.ajax.jq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kh.ajax.jq.model.vo.Member;

public class MemberDAO {

	public int idDupCheck(Connection conn, String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query 
		 	= "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_STATUS='Y'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
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

	
	
	public Member selectMember(Connection conn ,String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query 
		 	= "SELECT MEMBER_ID, MEMBER_NM,MEMBER_EMAIL,MEMBER_INTEREST, MEMBER_ENROLL_DATE FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_STATUS='Y' ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getString("MEMBER_ID"), 
						rset.getString("MEMBER_NM"), 
						rset.getString("MEMBER_EMAIL"), 
						rset.getString("MEMBER_INTEREST"),
						rset.getTimestamp("MEMBER_ENROLL_DATE"));
				
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return member;
	}

}
