package com.gy.wsp.board.model.vo;

public class Attachment {
	private int fileNo;
	private String fileOriginName;
	private String fileChangeName;
	private String filePath;
	private int parentBoardNo;
	private int fileLevel;
	
	public Attachment() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public Attachment(String fileOriginName, String fileChangeName, String filePath, int parentBoardNo, int fileLevel) {
		super();
		this.fileOriginName = fileOriginName;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.parentBoardNo = parentBoardNo;
		this.fileLevel = fileLevel;
	}





	public Attachment(int fileNo, String fileChangeName, String filePath, int parentBoardNo) {
		super();
		this.fileNo = fileNo;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.parentBoardNo = parentBoardNo;
	}



	public Attachment(int fileNo, String fileOriginName, String fileChangeName, String filePath, int parentBoardNo,
			int fileLevel) {
		super();
		this.fileNo = fileNo;
		this.fileOriginName = fileOriginName;
		this.fileChangeName = fileChangeName;
		this.filePath = filePath;
		this.parentBoardNo = parentBoardNo;
		this.fileLevel = fileLevel;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileOriginName() {
		return fileOriginName;
	}

	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}

	public String getFileChangeName() {
		return fileChangeName;
	}

	public void setFileChangeName(String fileChangeName) {
		this.fileChangeName = fileChangeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getParentBoardNo() {
		return parentBoardNo;
	}

	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", fileOriginName=" + fileOriginName + ", fileChangeName="
				+ fileChangeName + ", filePath=" + filePath + ", parentBoardNo=" + parentBoardNo + ", fileLevel="
				+ fileLevel + "]";
	}
	
	
	
	

}
