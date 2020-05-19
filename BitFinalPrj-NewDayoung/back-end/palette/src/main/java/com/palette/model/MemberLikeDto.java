package com.palette.model;

public class MemberLikeDto {

    private int groupLikeSeq;
    private int groupInfoSeq;
    private int memberSeq;
    private String groupName;
    private int del;

    public MemberLikeDto() {
    }

    public MemberLikeDto(int groupLikeSeq, int groupInfoSeq, int memberSeq, String groupName, int del) {
        this.groupLikeSeq = groupLikeSeq;
        this.groupInfoSeq = groupInfoSeq;
        this.memberSeq = memberSeq;
        this.groupName = groupName;
        this.del = del;
    }

    public int getGroupLikeSeq() {
        return this.groupLikeSeq;
    }

    public void setGroupLikeSeq(int groupLikeSeq) {
        this.groupLikeSeq = groupLikeSeq;
    }

    public int getGroupInfoSeq() {
        return this.groupInfoSeq;
    }

    public void setGroupInfoSeq(int groupInfoSeq) {
        this.groupInfoSeq = groupInfoSeq;
    }

    public int getMemberSeq() {
        return this.memberSeq;
    }

    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getDel() {
        return this.del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public MemberLikeDto groupLikeSeq(int groupLikeSeq) {
        this.groupLikeSeq = groupLikeSeq;
        return this;
    }

    public MemberLikeDto groupInfoSeq(int groupInfoSeq) {
        this.groupInfoSeq = groupInfoSeq;
        return this;
    }

    public MemberLikeDto memberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
        return this;
    }

    public MemberLikeDto groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public MemberLikeDto del(int del) {
        this.del = del;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " groupLikeSeq='" + getGroupLikeSeq() + "'" +
            ", groupInfoSeq='" + getGroupInfoSeq() + "'" +
            ", memberSeq='" + getMemberSeq() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", del='" + getDel() + "'" +
            "}";
    }

}