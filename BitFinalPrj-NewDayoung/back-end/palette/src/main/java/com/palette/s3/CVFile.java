package com.palette.s3;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CVFile {
	private int cvSeq; // CV_SEQ
	private int memberSeq; // MEMBER_SEQ
	private String memberId; // MEMBER_ID
	private String title; // TITLE
	private String category; // CATEGORY
	private String writeDate; // WRITE_DATE
	private int readCount; // READ_COUNT
	private String fileName; // FILE_NAME
	private String dbFileName; // DB_FILE_NAME
	private int del; // DEL
	private List<MultipartFile> files;
	
	public CVFile() {
	}

	public CVFile(int cvSeq, int memberSeq, String memberId, String title, String category, String writeDate,
			int readCount, String fileName, String dbFileName, int del, List<MultipartFile> files) {
		super();
		this.cvSeq = cvSeq;
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.title = title;
		this.category = category;
		this.writeDate = writeDate;
		this.readCount = readCount;
		this.fileName = fileName;
		this.dbFileName = dbFileName;
		this.del = del;
		this.files = files;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "CVFile [cvSeq=" + cvSeq + ", memberSeq=" + memberSeq + ", memberId=" + memberId + ", title=" + title
				+ ", category=" + category + ", writeDate=" + writeDate + ", readCount=" + readCount + ", fileName="
				+ fileName + ", dbFileName=" + dbFileName + ", del=" + del + ", files=" + files + "]";
	}

	
		
	
}
