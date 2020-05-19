package com.palette.model;

public class CommentDto {

	private int boardCommentSeq;
	private int boardSeq;
	private int memberSeq;
	private int ref;
	private int step;
	private int depth;
	private String content;
	private String writeDate;
	private int del;
	
	private String memberId;
	
	public CommentDto() {
	}

	
	public CommentDto(int boardCommentSeq, int boardSeq, int memberSeq, int ref, int step, int depth, String content,
			String writeDate, int del, String memberId) {
		super();
		this.boardCommentSeq = boardCommentSeq;
		this.boardSeq = boardSeq;
		this.memberSeq = memberSeq;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.content = content;
		this.writeDate = writeDate;
		this.del = del;
		this.memberId = memberId;
	}
	

	@Override
	public String toString() {
		return "CommentDto [boardCommentSeq=" + boardCommentSeq + ", boardSeq=" + boardSeq + ", memberSeq=" + memberSeq
				+ ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", content=" + content + ", writeDate="
				+ writeDate + ", del=" + del + ", memberId=" + memberId + "]";
	}


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBoardCommentSeq() {
		return boardCommentSeq;
	}

	public void setBoardCommentSeq(int boardCommentSeq) {
		this.boardCommentSeq = boardCommentSeq;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
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
}
