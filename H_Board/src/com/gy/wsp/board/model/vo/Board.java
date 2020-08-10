package com.gy.wsp.board.model.vo;

import java.sql.Timestamp;

public class Board {
	private int boardNo;  // 게시판 번호
	private String boardTitle; // 제목
	private String boardContent; // 내용
	private Timestamp boardCreateDate; // 등록일자
	private int readCount; // 조회수
	private String memberEmail; // 회원이메일 
	private String boardStatus; // 게시판상태
	private String categoryName; // 게시판종류
	private int boardType; // 게시판타입
	
	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public Board(int boardNo, String boardTitle, String boardContent, Timestamp boardCreateDate, int readCount,
			 String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.readCount = readCount;
		this.categoryName = categoryName;
	}





	public Board(String boardTitle, String boardContent, String memberEmail, String categoryName) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberEmail = memberEmail;
		this.categoryName = categoryName;
	}




	public Board(int boardNo, String boardTitle, String boardContent, Timestamp boardCreateDate, int readCount,
			String memberEmail, String boardStatus, String categoryName, int boardType) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.readCount = readCount;
		this.memberEmail = memberEmail;
		this.boardStatus = boardStatus;
		this.categoryName = categoryName;
		this.boardType = boardType;
	}
	
	
	
	

	public Board(int boardNo, String boardTitle, Timestamp boardCreateDate, int readCount, String memberEmail,
			String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCreateDate = boardCreateDate;
		this.readCount = readCount;
		this.memberEmail = memberEmail;
		this.categoryName = categoryName;
	}
	
	



	public Board(int boardNo, String boardTitle, String boardContent, Timestamp boardCreateDate,  int readCount, String memberEmail,
			String categoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.readCount = readCount;
		this.memberEmail = memberEmail;
		this.categoryName = categoryName;
	}




	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Timestamp getBoardCreateDate() {
		return boardCreateDate;
	}

	public void setBoardCreateDate(Timestamp boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	
	
	
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCreateDate=" + boardCreateDate + ", readCount=" + readCount + ", memberEmail=" + memberEmail
				+ ", boardStatus=" + boardStatus + ", categoryName=" + categoryName + ", boardType=" + boardType + "]";
	}
	
	
	
	

}
