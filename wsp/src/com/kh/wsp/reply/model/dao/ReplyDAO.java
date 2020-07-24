package com.kh.wsp.reply.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.wsp.reply.model.vo.Reply;

import javafx.css.PseudoClass;

public class ReplyDAO {
	private Properties prop;
	
	public ReplyDAO() throws Exception{
		String fileName 
        = ReplyDAO.class.getResource("/com/kh/wsp/sql/reply/reply-query.properties").getPath();  
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
		
	}

	/** 댓글 목록 조회 DAO
	 * @param conn 
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	   public List<Reply> selectList(Connection conn, int boardNo)throws Exception {
		      PreparedStatement pstmt = null;
		      ResultSet rset = null;
		      List<Reply> rList = null;
		      
		      String query = prop.getProperty("selectList");
		      
		      try {
		         pstmt = conn.prepareStatement(query);
		         pstmt.setInt(1, boardNo);
		         rset = pstmt.executeQuery();
		         
		         rList = new ArrayList<>();
		         
		         Reply reply = null; // 반복사용할 참조변수
		         while(rset.next()) {
		            reply = new Reply(rset.getInt("REPLY_NO"),
		                              rset.getString("REPLY_CONTENT"),
		                              rset.getString("MEMBER_ID"),
		                              rset.getTimestamp("REPLY_CREATE_DT"),
		                              rset.getTimestamp("REPLY_MODIFY_DT")
		                              );
		            rList.add(reply);
		                     }
		         
		      }finally {
		         rset.close();
		         pstmt.close();
		      }

		      return rList;
		   }

	/** 댓글 등록 DAO
	 * @param conn
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Connection conn, Reply reply) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getParentBoardNo());
			pstmt.setString(3, reply.getMemberId());
			
			result = pstmt.executeUpdate();
		
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 댓글 삭제 DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn, int replyNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteReply");
			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}
	
	
}
