package com.palette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.MemberDto;
import com.palette.service.AdminCompanyService;


@CrossOrigin(origins = "*")
@RestController
public class Admin_CompanyController {
	@Autowired
	AdminCompanyService adminCompanyService;
	

// 	show all company
	@PostMapping(value = "/adminCompanyMemberShowAll")
    public List<MemberDto> getCompanyListAll() {
    	System.out.println("getCompanyListAll Method execute Now..");
    	
    	List<MemberDto> list = adminCompanyService.adminGetCompanyMemberListAll();
    	System.out.println("getCompanyListAll RESULT : \n" + list );
    	return list;
    }
    

//	MemberDto adminGetCompanyInfoOne(MemberDto dto);    
    @GetMapping(value = "/showCompanyInfo")
    public MemberDto adminGetCompanyInfoOne(MemberDto dto) {
    	System.out.println("adminGetCompanyInfoOne Method execute Now..");
    	
    	MemberDto resdto = adminCompanyService.adminGetCompanyInfoOne(dto);
    	System.out.println("adminGetCompanyInfoOne RESULT : \n" + resdto );
    	return resdto;
    }
    
//	MemberDto approvalrequestCompanyList();
    @PostMapping(value = "/adminGetCompanyInfoOne")
    public MemberDto approvalrequestCompanyList(MemberDto dto) {
    	System.out.println("approvalrequestCompanyList Method execute Now..\n\n");
		System.out.println("dto");
		System.out.println(dto);
    	MemberDto resdto = adminCompanyService.adminGetCompanyInfoOne(dto);
    	System.out.println("\n\n approvalrequestCompanyList RESULT : \n" + dto + "\n\n");
    	return resdto;
    }
    
    
//	MemberDto finishApprovalCompanyList();
    // @GetMapping(value = "/finishApprovalCompanyList")
    // public MemberDto finishApprovalCompanyList() {
    // 	System.out.println("finishApprovalCompanyList Method execute Now..");
    	
    // 	MemberDto dto = adminCompanyService.finishApprovalCompanyList();
    // 	System.out.println("adminGetCompanyMemberListAll RESULT : \n" + dto );
    // 	return dto;
    // }

//	List<String> adminGetCompanyLocationALl();
    // @GetMapping(value = "/adminGetCompanyLocationALl")
    // public List<String> adminGetCompanyLocationALl() {
    // 	System.out.println("adminGetCompanyLocationALl Method execute Now..");
    	
    // 	List<String> list = adminCompanyService.adminGetCompanyLocationALl();
    // 	System.out.println("adminGetCompanyMemberListAll RESULT : \n" + list );
    // 	return list;
    // }
    
    
//	List<String> adminGetCompanyNameAll();
    @PostMapping(value = "/adminGetCompanyNameAll")
    public List<String> adminGetCompanyNameAll() {
    	System.out.println("adminGetCompanyNameAll Method execute Now..");
    	
    	List<String> list = adminCompanyService.adminGetCompanyNameAll();
    	System.out.println("adminGetCompanyMemberListAll RESULT : \n" + list );
    	return list;
	}
	

	//changeAUTHforCompanyMember
	/*
		Table: MEMBER
		Column:AUTH
		Goal: Change AUTH column status '2' to '3' (watforCompanyMember => companyMember)
		Mapping id : changeAUTHforCompanyMember(Memberdto dto)
	*/
	@PostMapping(value = "/changeAuthCompanyMember")
    public boolean changeAuthCompanyMemberStatus(MemberDto dto) {
    	System.out.println("changeAuthCompanyMemberStatus Method execute Now..");
    	
    	boolean isS = adminCompanyService.changeAUTHforCompanyMember(dto);
    	System.out.println("changeAuthCompanyMemberStatus RESULT : \n" + isS );
    	return isS;
    }
    
    
    
	
    

}//end of Admin_CompanyMemController class
