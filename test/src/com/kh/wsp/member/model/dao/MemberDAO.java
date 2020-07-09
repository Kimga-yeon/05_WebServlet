package com.kh.wsp.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.kh.wsp.member.model.vo.Member;

public class MemberDAO {
	private Properties prop = null;
	public MemberDAO() throws FileNotFoundException, IOException {
		String fileName = MemberDAO.class.getResource("/com/kh/wsp/sql/member/member-query.properties").getPath();
				
		prop = new Properties();
		prop.load(new FileReader(fileName));
	
	}
	public Member loginMember(Connection conn, Member member) throws Exception{
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


	
	
}
