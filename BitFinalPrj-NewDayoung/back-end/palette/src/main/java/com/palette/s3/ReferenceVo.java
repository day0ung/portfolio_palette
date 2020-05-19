package com.palette.s3;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ReferenceVo {
	
	
    private int groupInfoSeq;
    private int boardSeq;
    private int memberSeq;
    private String title;
    private String memberId;
    private String content;

    private String pwd;
    private String memberName;
    private String email;
    private String address;
    private int auth;
    private String companyInfo;
    private List<MultipartFile> files;

    public ReferenceVo() {
    }

	public ReferenceVo(int groupInfoSeq, int boardSeq, int memberSeq, String memberId, String title, String content,
			List<MultipartFile> files, int auth, String pwd, String memberName, String email, String address,
			String companyInfo) {
		super();
		this.groupInfoSeq = groupInfoSeq;
		this.boardSeq = boardSeq;
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.files = files;
		this.auth = auth;
		this.pwd = pwd;
		this.memberName = memberName;
		this.email = email;
		this.address = address;
		this.companyInfo = companyInfo;
	}

	public int getGroupInfoSeq() {
		return groupInfoSeq;
	}

	public void setGroupInfoSeq(int groupInfoSeq) {
		this.groupInfoSeq = groupInfoSeq;
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

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	@Override
	public String toString() {
		return "ReferenceVo [groupInfoSeq=" + groupInfoSeq + ", boardSeq=" + boardSeq + ", memberSeq=" + memberSeq
				+ ", memberId=" + memberId + ", title=" + title + ", content=" + content + ", files=" + files
				+ ", auth=" + auth + ", pwd=" + pwd + ", memberName=" + memberName + ", email=" + email + ", address="
				+ address + ", companyInfo=" + companyInfo + "]";
	}
    
    

 

}