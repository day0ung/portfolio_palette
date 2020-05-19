package com.palette.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.AnonymousBoardDto;
import com.palette.model.BoardParams;
import com.palette.model.CommentDto;
import com.palette.model.MemberCVDto;
import com.palette.service.AnonymousBoardService;



@CrossOrigin(origins = "*")
@RestController
public class AnonymousBoardController {

	@Autowired
	AnonymousBoardService anonymousBoardService;
	
//  익명게시판 페이징
	@PostMapping(value="/anonymousBoardPagingList")
    public ArrayList<AnonymousBoardDto> getAnonymousBoardPagingList(BoardParams boardParams) {
		System.out.println("anonymousBoardPagingList() : "+ boardParams.toString());
    	boardParams.setStart( (boardParams.getPage()-1)*boardParams.getLimit() );
    	ArrayList<AnonymousBoardDto> list =	anonymousBoardService.getAnonymousBoardPagingList(boardParams);
    	for(int i = 0; i < list.size(); i++) {
    		String sFinal = list.get(i).getFinalnum()+"번째 글";
    		list.get(i).setSfinalnum(sFinal);
    		
    	}
    	return list;
    }
	
//	익명게시판 리스트 갯수
	@PostMapping(value = "/anonymousBoardList")
    public String anonymousBoardList(BoardParams boardParams) {
		System.out.println("anonymousBoardList() 실행");
		int total =	anonymousBoardService.anonymousBoardList(boardParams);
		String stotal = total + "";
		System.out.println("total :" + total);
		return stotal;
	}

//	익명게시판 추가
	@GetMapping(value = "/insertAnonymousBoard")
    public boolean insertAnonymousBoard(AnonymousBoardDto dto){
		System.out.println("insertAnonymousBoard() 실행");
		boolean isS = anonymousBoardService.insertAnonymousBoard(dto);
		return isS;
	}
	
	@PostMapping(value = "/anonymousBoardDetail")
    public AnonymousBoardDto anonymousBoardDetail(AnonymousBoardDto anonymousBoardDto) {
		System.out.println("anonymousBoardDetail() 실행");
		AnonymousBoardDto dto =	anonymousBoardService.anonymousBoardDetail(anonymousBoardDto);
		return dto;
	}
	
	@PostMapping(value = "/anonymousBoardDelete")
    public String anonymousBoardDelete(AnonymousBoardDto anonymousBoardDto) {
		System.out.println("anonymousBoardDelete() 실행");
		anonymousBoardService.anonymousBoardDelete(anonymousBoardDto);
		return "";
	}

// comment
	@PostMapping(value = "/noticeInsertComment")
	public String noticeInsertComment(CommentDto commentDto) {
		System.out.println("NoticeInsertComment()");
		anonymousBoardService.noticeInsertComment(commentDto);
		return "";
	}
	@PostMapping(value = "/noticeComments")
	public ArrayList<CommentDto> noticeComments(CommentDto commentDto){
		System.out.println("noticeComments()");
		ArrayList<CommentDto> list = anonymousBoardService.noticeComments(commentDto);
		
		return list;
	}
	@PostMapping(value = "/noticeRealAnswerUpdate")
	public String noticeRealAnswerUpdate(CommentDto commentDto) {
		System.out.println("noticeRealAnswerUpdate()");
		anonymousBoardService.noticeRealAnswerUpdate(commentDto);
		return "";
	}
	@PostMapping(value = "/noticeAnswerDelete")
	public String noticeAnswerDelete(CommentDto commentDto) {
		System.out.println("noticeAnswerDelete()");
		anonymousBoardService.noticeAnswerDelete(commentDto);
		return"";
	}
	
	@PostMapping(value = "/noticeAnswerInsert")
	public String noticeAnswerInsert(CommentDto commentDto) {
		System.out.println("noticeAnswerInsert()");
		anonymousBoardService.noticeAnswerInsert(commentDto);
		return"";
	}
}
