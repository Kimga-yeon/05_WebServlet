package com.kh.wsp.reply.model.vo;

import java.sql.Timestamp;

public class Reply {
	   private int replyNo;                 // 댓글 번호
	   private String replyContent;         // 댓글 내용
	   private int parentBoardNo;           // 댓글이 작성된 게시글 번호
	   private String memberId;             // 댓글 작성 회원
	   private Timestamp replyCreateDate;   // 댓글 작성일
	   private Timestamp replyModifyDate;   // 댓글 수정일
	   private String replyStatus;          // 댓글 상태
	   
	   public Reply() {
	}
	   
	   
	   
	   

	public Reply(String replyContent, int parentBoardNo, String memberId) {
		super();
		this.replyContent = replyContent;
		this.parentBoardNo = parentBoardNo;
		this.memberId = memberId;
	}





	public Reply(int replyNo, String replyContent, String memberId, Timestamp replyCreateDate,
			Timestamp replyModifyDate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.memberId = memberId;
		this.replyCreateDate = replyCreateDate;
		this.replyModifyDate = replyModifyDate;
	}




	public Reply(int replyNo, String replyContent, int parentBoardNo, String memberId, Timestamp replyCreateDate,
			Timestamp replyModifyDate, String replyStatus) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.parentBoardNo = parentBoardNo;
		this.memberId = memberId;
		this.replyCreateDate = replyCreateDate;
		this.replyModifyDate = replyModifyDate;
		this.replyStatus = replyStatus;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getParentBoardNo() {
		return parentBoardNo;
	}

	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Timestamp getReplyCreateDate() {
		return replyCreateDate;
	}

	public void setReplyCreateDate(Timestamp replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}

	public Timestamp getReplyModifyDate() {
		return replyModifyDate;
	}

	public void setReplyModifyDate(Timestamp replyModifyDate) {
		this.replyModifyDate = replyModifyDate;
	}

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", parentBoardNo=" + parentBoardNo
				+ ", memberId=" + memberId + ", replyCreateDate=" + replyCreateDate + ", replyModifyDate="
				+ replyModifyDate + ", replyStatus=" + replyStatus + "]";
	}
	   
	   
	   
}
