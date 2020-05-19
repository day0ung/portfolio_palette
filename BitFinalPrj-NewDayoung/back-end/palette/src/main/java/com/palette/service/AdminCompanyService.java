package com.palette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.adminCompanyDao;
import com.palette.model.MemberDto;


@Service
@Transactional
public class AdminCompanyService {
	@Autowired
	adminCompanyDao adminmemberdao;
	
	public List<MemberDto> adminGetCompanyMemberListAll(){
		return adminmemberdao.adminGetCompanyMemberListAll();
	}
	
	
	// public MemberDto approvalrequestCompanyList(MemberDto dto){
	// 	return adminmemberdao.approvalrequestCompanyList(dto);
	// }
	
	// public MemberDto finishApprovalCompanyList(MemberDto dto) {
	// 	return adminmemberdao.finishApprovalCompanyList(dto);
	// }
	
	// public List<String> adminGetCompanyLocationALl(){
	// 	return adminmemberdao.adminGetCompanyLocationALl();
		
	// }
	
	public MemberDto adminGetCompanyInfoOne(MemberDto dto) {
		
		return adminmemberdao.adminGetCompanyInfoOne(dto);
	}
	
	public List<String> adminGetCompanyNameAll(){
		return adminmemberdao.adminGetCompanyNameAll();
	}

	
	public boolean changeAUTHforCompanyMember(MemberDto dto){
		return adminmemberdao.changeAUTHforCompanyMember(dto);
	}

	

	
	
	

}// end of adminCompanyService class
