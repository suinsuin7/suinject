package kr.or.ddit.vo;

import java.util.Date;

public class PurInfoVO {
	
	private String username;
	private String bookId;
	private int purCnt;
	private Date purDt;
	
	public PurInfoVO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public int getPurCnt() {
		return purCnt;
	}

	public void setPurCnt(int purCnt) {
		this.purCnt = purCnt;
	}

	public Date getPurDt() {
		return purDt;
	}

	public void setPurDt(Date purDt) {
		this.purDt = purDt;
	}
	


}
