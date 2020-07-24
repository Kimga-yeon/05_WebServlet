package com.kh.wsp.board.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.wsp.board.model.vo.Attachment;
import com.kh.wsp.board.model.vo.Board;
import com.kh.wsp.board.model.vo.PageInfo;

public class BoardDAO {
	private Properties prop;
	
	public BoardDAO() throws Exception{
		String fileName 
    	= BoardDAO.class.getResource("/com/kh/wsp/sql/board/board-query.properties").getPath();  
	
	prop = new Properties();
	
	prop.load(new FileReader(fileName));
	
	}

	/** 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param boardType
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int boardType) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardType);
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
	public List<Board> selectList(Connection conn, PageInfo pInfo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> bList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			// SQL 조건절에 사용할 값을 가공
			int startRow = (pInfo.getCurrentPage()-1)* pInfo.getLimit()+1;
			
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pInfo.getBoardType());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			
			Board board = null;
			while(rset.next()) {
				board = new Board(rset.getInt("BOARD_NO"), 
		                  rset.getString("BOARD_TITLE"), 
		                  rset.getString("MEMBER_ID"), 
		                  rset.getInt("READ_COUNT"), 
		                  rset.getTimestamp("BOARD_CREATE_DT"),
		                  rset.getString("CATEGORY_NM")
		                  );
				bList.add(board);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		return bList;
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

	/** 게시글 삽입 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getMemberId());
			pstmt.setString(5, board.getCategoryName());
			pstmt.setInt(6, board.getBoardType());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result;
	}

	/** 파일 등록 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, at.getParentBoardNo());
			pstmt.setString(2, at.getFileOriginName());
			pstmt.setString(3, at.getFileChangeName());
			pstmt.setString(4, at.getFilePath());
			pstmt.setInt(5, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		
		
		return result;
	}

	/** 썸네일 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFileList(Connection conn, PageInfo pInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectFileList");

		try {
			int startRow = (pInfo.getCurrentPage()-1)* pInfo.getLimit()+1;

			int endRow = startRow + pInfo.getLimit() -1;

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pInfo.getBoardType());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			
			fList = new ArrayList<>();
			
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(
						rset.getInt("FILE_NO"),
						rset.getInt("PARENT_BOARD_NO"),
						rset.getString("FILE_CHANGE_NAME"),
						rset.getString("FILE_PATH")
						);
				fList.add(at);
			}

		}finally {
			rset.close();
			pstmt.close();
		}
		return fList;
	}

	/** 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception{
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
		                  rset.getString("MEMBER_ID"),
		                  rset.getInt("READ_COUNT"),
		                  rset.getTimestamp("BOARD_CREATE_DT"),
		                  rset.getTimestamp("BOARD_MODIFY_DT"),
		                  rset.getString("CATEGORY_NM")
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
	public List<Attachment> selectFiles(Connection conn, int boardNo) throws Exception{
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

	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 게시글 수정 화면 구성 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board updateView(Connection conn, int boardNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		String query = prop.getProperty("updateView");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board(
						boardNo, 
						rset.getString("BOARD_TITLE"), 
						rset.getString("BOARD_CONTENT"), 
						rset.getString("BOARD_CATEGORY"));
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return board;
	}

	/** 게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board) throws Exception{
	      PreparedStatement pstmt = null;
	      int result = 0;
	      String query =prop.getProperty("updateBoard");
	      
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, board.getBoardTitle());
	         pstmt.setString(2, board.getBoardContent());
	         pstmt.setString(3, board.getCategoryName());
	         pstmt.setInt(4, board.getBoardNo());
	         
	         result = pstmt.executeUpdate();

	      }finally {
	         pstmt.close();
	      }
	      
	      return result;
	   }

	/** 파일 정보 수정 DAO
	 * @param conn
	 * @param newFile
	 * @return result
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, Attachment newFile) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newFile.getFileOriginName());
			pstmt.setString(2, newFile.getFileChangeName());
			pstmt.setString(3, newFile.getFilePath());
			pstmt.setInt(4, newFile.getFileNo());
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;
	}
}
