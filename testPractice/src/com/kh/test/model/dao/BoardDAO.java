package com.kh.test.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kh.test.model.vo.TestBoard;

public class BoardDAO {
	
	private PreparedStatement pstmt;
	private ResultSet rset;
	private DataSource ds;
	private Connection conn;
	
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/oracle");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			if(pstmt != null) { pstmt.close(); pstmt = null;}
			if(rset != null) { pstmt.close(); rset = null;}
			if(conn != null) { conn.close(); conn = null;}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 게시글 목록 조회 DAO
	 * @return list
	 * @throws Exception
	 */
	public List<TestBoard> selectList() throws Exception{
		List<TestBoard> list = null;
		
		
		try {
			conn = ds.getConnection();
			String query = "SELECT * FROM TESTBOARD ORDER BY BOARD_NO DESC";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<TestBoard>();
			TestBoard board = null;
			while(rset.next()) {
				board = new TestBoard(rset.getInt(1), 
						rset.getString(2),rset.getString(3),rset.getDate(4));
				
				list.add(board);
			}
				
		}finally {
			close();
		}
		
		return list;
	}

	public int insertBoard(TestBoard board) throws Exception {
		
		int result = 0;
		
		String query = "INSERT INTO TESTBOARD VALUES"
				+ "(SEQ_BNO.NEXTVAL,?,?,DEFAULT)";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
		
			result = pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		return result;
	}
}
