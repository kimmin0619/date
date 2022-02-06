package com.date.jum5.review.vo;

public class ReviewVo {

	int seq;
	String title;
	String content;
	String name;
	String reg_date;
	String readCount;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	
	@Override
	public String toString() {
		return "ReviewVo [seq=" + seq + ", title=" + title + " , content=" + content + " , name=" + name + " , reg_date=" + reg_date + ""
				+ " readCount=" + readCount + "]";
	}
}
