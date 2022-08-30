package com.ezen.biz.dto;

import java.util.Date;

public class ReviewVO {
	private int rseq;
	private int pseq;
	private String content;
	private String id;
	private Date regdate;
	
	
	public int getRseq() {
		return rseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
	}
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ReviewVO [rseq=" + rseq + ", pseq=" + pseq + ", content=" + content + ", id=" + id + ", regdate="
				+ regdate + "]";
	}
	
	
}
