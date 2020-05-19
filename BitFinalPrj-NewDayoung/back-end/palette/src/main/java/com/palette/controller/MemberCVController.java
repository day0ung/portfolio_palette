package com.palette.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.BoardParams;
import com.palette.model.BoardReferenceDto;
import com.palette.model.CVReferenceDto;
import com.palette.model.EmploymentBoardDto;
import com.palette.model.GroupBoardDto;
import com.palette.model.MemberCVDto;
import com.palette.model.MemberDto;
import com.palette.s3.CVFile;
import com.palette.s3.ReferenceVo;
import com.palette.service.MemberCVService;

@CrossOrigin(origins = "*")
@RestController
public class MemberCVController {
	@Autowired
	MemberCVService memberCVService;
	
//	CV 추가
    @PostMapping(value = "/insertCV")
    public String insertCV(CVFile form) throws IOException{
    	memberCVService.CVNumChange(form.getMemberSeq());
    	
    	memberCVService.insertCV(form);
        return "";
    }

//	CV 유무변경
    
    
	
//  cv페이징
	@PostMapping(value="/CVPagingList")
    public ArrayList<MemberCVDto> getCVPagingList(BoardParams boardParams) {
		System.out.println("getGroupPagingList() : "+boardParams.toString());
    	boardParams.setStart( (boardParams.getPage()-1)*boardParams.getLimit() );
    	ArrayList<MemberCVDto> list =	memberCVService.getCVPagingList(boardParams);
    	return list;
    }
	
//	cv 리스트 갯수
	@PostMapping(value = "/CVList")
    public String CVList(BoardParams boardParams) {
		System.out.println("CVLIST() 실행");
		int total =	memberCVService.CVList(boardParams);
		String stotal = total + "";
		System.out.println("total :" + total);
		return stotal;
	}
	
//	cv 디테일(cvSeq)
	@PostMapping(value = "/getOneCV")
    public MemberCVDto getOneCV(int cvSeq) {
		System.out.println("getOneCV() 실행");
		System.out.println("cvSeq:"+ cvSeq);
		// 조회수 증가
		memberCVService.readCount(cvSeq);
		// cv 객체
		MemberCVDto dto = memberCVService.getOneCV(cvSeq);
		
		
		return dto;
	}
	
//	cv 디테일(memberSeq)
	@PostMapping(value = "/getOneCVByMemberSeq")
    public MemberCVDto getOneCVByMemberSeq(int memberSeq) {
		System.out.println("getOneCV() 실행");
		System.out.println("memberSeq:"+ memberSeq);

		// cv 객체
		MemberCVDto dto = memberCVService.getOneCVByMemberSeq(memberSeq);
		
		
		return dto;
	}
	
// 단일멤버 객체 가지고 오기
	@PostMapping(value= "/oneMember")
    public MemberDto oneMember(int memberSeq) {
    	System.out.println("oneMember() 실행");
    	System.out.println("======"+ memberSeq);
    	MemberDto member = memberCVService.oneMember(memberSeq);
    	return member;
	}
	
//  CV 수정
	@GetMapping(value = "/updateCV")
    public boolean updateCV(MemberCVDto dto){
    	System.out.println("updateCV() 실행");
    	
    	boolean isS = memberCVService.updateCV(dto);
    	
    	return isS;
    }
	
//	cvSeq로 이력서 파일 불러오기
	@PostMapping(value = "/cvDetailRef")
    public CVReferenceDto cvDetailRef(int cvSeq){
        System.out.println("cvDetailRef()" + cvSeq);
        CVReferenceDto dto = memberCVService.cvDetailRef(cvSeq);
        System.out.println("파일" + dto);
        return dto;
    }
	
//	memberSeq로 이력서 파일 불러오기
	@PostMapping(value = "/cvDetailRefByMemberSeq")
    public CVReferenceDto cvDetailRefByMemberSeq(int memberSeq){
        System.out.println("cvDetailRefByMemberSeq()" + memberSeq);
        CVReferenceDto dto = memberCVService.cvDetailRefByMemberSeq(memberSeq);
        System.out.println("파일" + dto);
        return dto;
    }
	
	
	
}
