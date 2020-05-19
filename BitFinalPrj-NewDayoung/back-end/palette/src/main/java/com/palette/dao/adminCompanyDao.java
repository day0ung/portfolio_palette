package com.palette.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.MemberDto;

@Mapper
@Repository
public interface adminCompanyDao {
	
	List<MemberDto> adminGetCompanyMemberListAll();
	
	
	// MemberDto approvalrequestCompanyList(MemberDto dto);
	
	// MemberDto finishApprovalCompanyList(MemberDto dto);
	
	List<String> adminGetCompanyLocationALl();
	
	List<String> adminGetCompanyNameAll();

	MemberDto adminGetCompanyInfoOne(MemberDto dto);
	
	boolean changeAUTHforCompanyMember(MemberDto dto);
	
	
}
