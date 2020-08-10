package com.gy.wsp.board.model.service;

import static com.gy.wsp.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.gy.wsp.board.model.dao.BoardDAO;
import com.gy.wsp.board.model.vo.Attachment;
import com.gy.wsp.board.model.vo.Board;
import com.gy.wsp.board.model.vo.PageInfo;


public class BoardService {

	private BoardDAO dao;

	public BoardService() throws Exception{
		dao = new BoardDAO();
	}

	
	
	/** 페이징 처리 정보 생성 Service
	 * @param currentPage
	 * @param boardType
	 * @return
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String currentPage) throws Exception{
		Connection conn = getConnection();

		int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);

		int listCount = dao.getListCount(conn);

		conn.close();

		return new PageInfo(cp, listCount);
	}


	/** 게시글 목록 조회 Service
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();

		List<Board> bList = dao.selectList(conn, pInfo);

		conn.close();

		return bList;
	}




	/** 게시글(글 + 파일) 등록 Service
	 * @param board
	 * @param fList
	 * @return Exception
	 */
	public int insertBoard(Board board, List<Attachment> fList) throws Exception {
		Connection conn = getConnection();
		int result = 0;

		// 다음 게시글 번호 얻어오기
		int boardNo = dao.selectNextNo(conn);

		if(boardNo > 0) {
			board.setBoardNo(boardNo);


			board.setBoardContent(replaceParameter(board.getBoardContent())); // 크로스 사이트 스크립팅 방지
			board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>")); // 개행문자 처리

			// 게시글 DB 삽입
			result = dao.insertBoard(conn,board);

			if(result > 0 && !fList.isEmpty()) {
				result = 0;

				for(Attachment at : fList) {
					at.setParentBoardNo(boardNo);

					result = dao.insertAttachment(conn, at);
					if(result == 0) break;
					}
			}
		}
		if(result > 0 )	{
			result = boardNo;
			conn.commit();
		}else {
			for(Attachment at : fList) {
				String filePath = at.getFilePath();
				String fileName = at.getFileChangeName();
				
				File deleteFile = new File(filePath + fileName);
				deleteFile.delete();
			}
			
			conn.rollback();
		}


		conn.close();
		return result;
	}
	
	// 크로스 사이트 스크립트 방지 메소드
	private String replaceParameter(String param) {
		String result = param;
		if(param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}

		return result;
	}



	/** 썸네일 목록 조회 Service
	 * @param pInfo
	 * @return	fList
	 * @throws Exception
	 */
	public List<Attachment> selectFileList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		List<Attachment> fList =dao.selectFileList(conn,pInfo);
		
		conn.close();
		return fList;
	}

	
	 /** 게시글 상세조회 Service
     * @param boardNo
     * @return board
     * @throws Exception
     */
	public Board selectBoard(int boardNo) throws Exception {
		 Connection conn = getConnection();
	       Board board = dao.selectBoard(conn, boardNo);
	       conn.close();
	       return board;
	    }


	
	 /** 게시글에 포함된 이미지 조회 Service
     * @param boardNo
     * @return fList
     * @throws Exception
     */
	public List<Attachment> selectFiles(int boardNo) throws Exception {
		Connection conn = getConnection();
		List<Attachment> fList = dao.selectFiles(conn, boardNo);
		conn.close();
		return fList;
	}

	
	
	
	
}	

