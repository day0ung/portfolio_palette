package com.palette.model;

public class CVReferenceDto {

    private int boardReferenceSeq;
    private int cvSeq;
    private int memberSeq;
    private String url;
    private String fileName;
    private int del;

    public CVReferenceDto() {
    }

	public CVReferenceDto(int boardReferenceSeq, int cvSeq, int memberSeq, String url, String fileName, int del) {
		super();
		this.boardReferenceSeq = boardReferenceSeq;
		this.cvSeq = cvSeq;
		this.memberSeq = memberSeq;
		this.url = url;
		this.fileName = fileName;
		this.del = del;
	}

	public int getBoardReferenceSeq() {
		return boardReferenceSeq;
	}

	public void setBoardReferenceSeq(int boardReferenceSeq) {
		this.boardReferenceSeq = boardReferenceSeq;
	}

	public int getCvSeq() {
		return cvSeq;
	}

	public void setCvSeq(int cvSeq) {
		this.cvSeq = cvSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "CVReferenceDto [boardReferenceSeq=" + boardReferenceSeq + ", cvSeq=" + cvSeq + ", memberSeq="
				+ memberSeq + ", url=" + url + ", fileName=" + fileName + ", del=" + del + "]";
	}

	

    
}