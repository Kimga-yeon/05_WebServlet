package com.gy.wsp.board.model.vo;

public class PageInfo {
	
	private int currentPage;  		// 현재 페이지 번호
	private int listCount;			// 전체 게시글의 수 
	private int limit = 10;				// 한 페이지에 보여질 게시글의 수 
	private int pagingBarSize = 10; 		// 화면에 표시될 페이징바의 페이지 개수
	
	private int maxPage;			// 전체 페이지 중 제일 마지막 페이지
	private int startPage;			// 페이징바 시작 페이지 번호
	private int endPage;			// 페이징바 끝 페이지 번호
	
	private int boardType;
	
	
	
	
	
	public PageInfo(int currentPage, int listCount) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
	}


	public PageInfo(int currentPage, int listCount, int boardType) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.boardType = boardType;
		makePageInfo();
	}


	public PageInfo(int currentPage, int listCount, int limit, int pagingBarSize, int boardType) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pagingBarSize = pagingBarSize;
		this.boardType = boardType;
		makePageInfo();
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		makePageInfo();
	}


	public int getListCount() {
		return listCount;
	}


	public void setListCount(int listCount) {
		this.listCount = listCount;
		makePageInfo();
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
		makePageInfo();
	}


	public int getPagingBarSize() {
		return pagingBarSize;
	}


	public void setPagingBarSize(int pagingBarSize) {
		this.pagingBarSize = pagingBarSize;
		makePageInfo();
	}


	public int getMaxPage() {
		return maxPage;
	}



	public int getStartPage() {
		return startPage;
	}



	public int getEndPage() {
		return endPage;
	}




	public int getBoardType() {
		return boardType;
	}


	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}


	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pagingBarSize=" + pagingBarSize + ", maxPage=" + maxPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", boardType=" + boardType + "]";
	}
	

	private void makePageInfo() {
		
		maxPage = (int)Math.ceil((double)listCount / limit);
		startPage = (currentPage-1)/pagingBarSize * pagingBarSize + 1;
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}else {
			endPage = startPage + pagingBarSize -1;
		}
	}
	
	
}
