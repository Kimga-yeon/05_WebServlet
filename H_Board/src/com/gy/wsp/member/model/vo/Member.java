package com.gy.wsp.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;  // 회원 번호
	private String memberEmail; //회원 이메일 
	private String memberName; //회원 이름 
	private String memberNick; // 닉네임
	private String memberPwd; //비밀번호
	private Date memberEnrollDate; //가입일
	private String memberGender;  //성별
	private String memberStatus; //회원 상태(정상 : 'Y' , 탈퇴 : 'N')
	private String memberGrade; //회원 등급(관리자 : 'A', 일반 : 'G')
	

	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Member(int memberNo, String memberEmail, String memberName, String memberNick, String memberPwd,
			Date memberEnrollDate, String memberGender, String memberStatus, String memberGrade) {
		super();
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.memberPwd = memberPwd;
		this.memberEnrollDate = memberEnrollDate;
		this.memberGender = memberGender;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
	}



	public Member(String memberEmail, String memberName, String memberNick, String memberPwd, String memberGender) {
		super();
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.memberPwd = memberPwd;
		this.memberGender = memberGender;
	}


	public Member(String memberEmail, String memberName, String memberNick, String memberGender) {
		super();
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.memberGender = memberGender;
	}


	public Member(String memberEmail, String memberPwd) {
		super();
		this.memberEmail = memberEmail;
		this.memberPwd = memberPwd;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberNick() {
		return memberNick;
	}


	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}


	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}


	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}


	public String getMemberGender() {
		return memberGender;
	}


	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}


	public String getMemberStatus() {
		return memberStatus;
	}


	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}


	public String getMemberGrade() {
		return memberGrade;
	}


	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	
	

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberName=" + memberName
				+ ", memberNick=" + memberNick + ", memberPwd=" + memberPwd + ", memberEnrollDate=" + memberEnrollDate
				+ ", memberGender=" + memberGender + ", memberStatus=" + memberStatus + ", memberGrade=" + memberGrade
				+ "]";
	}
	
	
	
	
	
}
