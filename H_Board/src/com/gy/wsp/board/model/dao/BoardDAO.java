package com.gy.wsp.board.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gy.wsp.board.model.vo.Attachment;
import com.gy.wsp.board.model.vo.Board;
import com.gy.wsp.board.model.vo.PageInfo;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class BoardDAO {
	private Properties prop;
	
	public BoardDAO() throws Exception{
		String fileName
			= BoardDAO.class.getResource("/com/gy/wsp/sql/board/board-query.properties").getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
	
	}
	

	/** 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param boardType
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) listCount = rset.getInt(1);
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return listCount;
		
	}

	/** 게시글 목록 조회용 DAO
	 * @param conn
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectList(Connection conn, PageInfo pInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> bList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			// SQL 조건절에 사용할 값을 가공
			int startRow = (pInfo.getCurrentPage()-1)* pInfo.getLimit()+1;
			
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			
			Board board = null;
			while(rset.next()) {
				board = new Board(rset.getInt("BOARD_NO"), 
		                  rset.getString("BOARD_TITLE"), 
		                  rset.getTimestamp("BOARD_CREATE_DT"),
		                  rset.getInt("READ_COUNT"), 
		                  rset.getString("MEMBER_EMAIL"), 
		                  rset.getString("CATEGORYNAME")
		                  );
				bList.add(board);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		return bList;
	}


	/** 썸네일 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFileList(Connection conn, PageInfo pInfo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectFileList");
		
		try {
			int startRow = (pInfo.getCurrentPage()-1)*pInfo.getLimit()+1;
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			fList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(
						rset.getInt("FILE_NO"),
						rset.getString("FILE_CHANGE_NAME"),
						rset.getString("FILE_PATH"),
						rset.getInt("PARENT_BOARD_NO")
						);
				fList.add(at);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		return fList;
	}


	/** 다음 게시글 번호 반환 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		// SELECT SEQ_BNO.NEXTBAL FROM DUAL
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
		}finally {
			rset.close();
			stmt.close();
		}
		
		return boardNo;
	}

	
	
	/** 파일 등록 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, at.getFileOriginName());
			pstmt.setString(2, at.getFileChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getParentBoardNo());
			pstmt.setInt(5, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		
		
		return result;
	}

	
	/** 게시글 삽입 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board)throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getMemberEmail());
			pstmt.setString(5, board.getCategoryName());
			
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result;
	}

	/** 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		String query = prop.getProperty("selectBoard");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			    board = new Board(
		                  rset.getInt("BOARD_NO"),
		                  rset.getString("BOARD_TITLE"),
		                  rset.getString("BOARD_CONTENT"),
		                  rset.getTimestamp("BOARD_CREATE_DT"),
		                  rset.getInt("READ_COUNT"),
		                  rset.getString("MEMBER_EMAIL"),
		                  rset.getString("CATEGORYNAME")
		                  );
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return board;
	}

	/** 게시글에 포함된 이미지 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectFiles");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Attachment>();
			Attachment file = null;
			while(rset.next()) {
	            file = new Attachment();
	            file.setFileNo(rset.getInt("FILE_NO"));
	            file.setFileChangeName(rset.getString("FILE_CHANGE_NAME"));
	            file.setFilePath(rset.getString("FILE_PATH"));
	            file.setFileLevel(rset.getInt("FILE_LEVEL"));
	            
	            fList.add(file);
	         }
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return fList;
		
	}

}
