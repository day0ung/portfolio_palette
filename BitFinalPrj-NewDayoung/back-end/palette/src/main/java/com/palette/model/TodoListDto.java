package com.palette.model;

public class TodoListDto {
	private int todoSeq;
	private int memberSeq;
	private String title;
	private String todoDate;
	private int del;
	
	
	private int delCount; //as
	
	public TodoListDto() {
		// TODO Auto-generated constructor stub
	}

	public TodoListDto(int todoSeq, int memberSeq, String title, String todoDate, int del, int delCount) {
		super();
		this.todoSeq = todoSeq;
		this.memberSeq = memberSeq;
		this.title = title;
		this.todoDate = todoDate;
		this.del = del;
		this.delCount = delCount;
	}

	public int getTodoSeq() {
		return todoSeq;
	}

	public void setTodoSeq(int todoSeq) {
		this.todoSeq = todoSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(String todoDate) {
		this.todoDate = todoDate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getDelCount() {
		return delCount;
	}

	public void setDelCount(int delCount) {
		this.delCount = delCount;
	}

	@Override
	public String toString() {
		return "TodoListDto [todoSeq=" + todoSeq + ", memberSeq=" + memberSeq + ", title=" + title + ", todoDate="
				+ todoDate + ", del=" + del + ", delCount=" + delCount + "]";
	}

	
	
	
	
}
