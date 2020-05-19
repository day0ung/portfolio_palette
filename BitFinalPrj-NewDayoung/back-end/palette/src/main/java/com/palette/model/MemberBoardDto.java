package com.palette.model;

import java.io.Serializable;

public class MemberBoardDto implements Serializable{
	
	private int rowNum;
	private int boardSeq;
	private int memberSeq;
	private String memberId;
	private String title;
	private String content;
	private String writeDate;
	private int del;
	
    private String image;
    private String fileName;
    private String dbFileName;
	
	public MemberBoardDto() {
		// TODO Auto-generated constructor stub
	}

	public MemberBoardDto(int rowNum, int boardSeq, int memberSeq, String memberId, String title, String content,
			String writeDate, int del, String image, String fileName, String dbFileName) {
		super();
		this.rowNum = rowNum;
		this.boardSeq = boardSeq;
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.del = del;
		this.image = image;
		this.fileName = fileName;
		this.dbFileName = dbFileName;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDbFileName() {
		return dbFileName;
	}

	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}

	@Override
	public String toString() {
		return "MemberBoardDto [rowNum=" + rowNum + ", boardSeq=" + boardSeq + ", memberSeq=" + memberSeq
				+ ", memberId=" + memberId + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", del=" + del + ", image=" + image + ", fileName=" + fileName + ", dbFileName=" + dbFileName + "]";
	}

	
	
	
}	
