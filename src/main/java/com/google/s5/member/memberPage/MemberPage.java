package com.google.s5.member.memberPage;

public class MemberPage {

	
	private Long curPage;
	private Integer perPage;
	private long startRow;
	private long lastRow;
	
	private long totalPage; //매개변수로 쓰는 이유는 나중에 꺼내 쓰기 위해서	
	private long totalBlock;	
	private long curBlock;
	private long startNum;
	private long lastNum;
	
	private String kind;
	private String search;
	
	
	public void makeRow() {
		this.startRow =((this.getCurPage()-1)*this.getPerPage()+1);
		this.lastRow=(this.getCurPage()*this.getPerPage());
	}
	
	public void makePage(long totalCount) {
		//noticeService에서 호출
		//1. totalCount : 전체 글의 갯수
		
		
		//2. totalCount로 totalPage 계산
		this.totalPage= totalCount/this.getPerPage();
		if(totalCount % this.getPerPage() !=0) {
			this.totalPage++;
		}
		
		//3.totalPage 로 totalBlock 계산
		long perBlock=5L;   //block page 수
		this.totalBlock=totalPage/perBlock;
		if(totalPage%perBlock!=0) {
			this.totalBlock++;
		}
		//4.curPage 로 curBlock을 찾기
		this.curBlock = this.curPage/perBlock;
		if(this.curPage%perBlock!=0) {
			this.curBlock++;
		}
		
		//5.curBlcok startNum, lastNum 계산
		this.startNum=(this.curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		if(this.curBlock==this.totalBlock) {
			this.lastNum=this.totalPage;
		}
		
	}//end method
	
	
	
	
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public long getTotalBlock() {
		return totalBlock;
	}

	public long getCurBlock() {
		return curBlock;
	}

	public long getStartNum() {
		return startNum;
	}

	public long getLastNum() {
		return lastNum;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public long getStartRow() {
		return startRow;
	}

	public long getLastRow() {
		return lastRow;
	}

	public Long getCurPage() {
		if(this.curPage==null || this.curPage==0) {
			this.curPage=1L;
		}
		return curPage;
	}
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	public Integer getPerPage() {
		//파라미터 값이 null일때 자동으로 들어올 수 있도록
		if(this.perPage==null || this.perPage==0) {
			this.perPage=10;
		}
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	
	
}
