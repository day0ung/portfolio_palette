package com.palette.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.BoardReferenceDto;
import com.palette.model.MemberBoardDto;
import com.palette.s3.ReferenceVo;
import com.palette.service.MemberBoardService;

@CrossOrigin(origins = "*")
@RestController
public class MemberBoardController {

	@Autowired
	MemberBoardService service;
	
	@PostMapping(value = "/getMemberBoard") 
	public List<MemberBoardDto> getMemberBoard(MemberBoardDto dto){
		List<MemberBoardDto> list = service.getMemberBoard(dto);
		for (MemberBoardDto memberBoardDto : list) {
			System.out.println("getMemBoard____"+memberBoardDto.toString());
		}
		return list;
	}
	
	//자료실 올리기
	@PostMapping(value = "/uploadBoard") 
	public String writeBoard(ReferenceVo form) throws IOException{
		System.out.println("uploadBoard");
		service.uploadBoard(form);
		return "";
	}
	
	//자료실 상세보기 게시판
	@GetMapping(value="/detailLibarary")
	public MemberBoardDto detailLibarary(int boardSeq) {
		System.out.println("detailLibarary"+ boardSeq);
		MemberBoardDto dto = service.detailLibarary(boardSeq);
		return dto;
	}
	
	//자료실 상세보기 파일
	@PostMapping(value="/detailFile")
	public List<BoardReferenceDto> detailFile(BoardReferenceDto dto) {
		System.out.println("detailFile"+dto.toString());
		List<BoardReferenceDto> list = service.detailFile(dto);
		return list;
	}
	
	//자료실 삭제
	@GetMapping(value = "/memberBoardDel") 
	public String memberBoardDel(int boardSeq) {
		System.out.println("memberBoardDel"+boardSeq);
		service.memberBoardDel(boardSeq);
		return "";
	}
	
	//자료실 수정
	@PostMapping(value = "/updateLibarary") 
	public String updateLibarary(ReferenceVo form) throws IOException{
		System.out.println("updateLibarary"+form.toString());
		service.updateLibarary(form);
		return "";
	}
	
}
