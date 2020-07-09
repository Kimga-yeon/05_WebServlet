package com.kh.test.model.vo;

import java.sql.Date;

public class TestBoard {
	private int boardNo;
	private String title;
	private String content;
	private Date createDate;
	
	public TestBoard() {
	}

	public TestBoard(int boardNo, String title, String content, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
	@Override
	public String toString() {
		return "testBoard [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", createDate="
				+ createDate + "]";
	}
	
	
	
	
}
