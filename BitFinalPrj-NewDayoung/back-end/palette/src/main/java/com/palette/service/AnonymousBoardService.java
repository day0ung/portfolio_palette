package com.palette.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.AnonymousBoardDao;
import com.palette.dao.EmploymentDao;
import com.palette.model.AnonymousBoardDto;
import com.palette.model.BoardParams;
import com.palette.model.CommentDto;
import com.palette.model.MemberCVDto;

@Service
@Transactional
public class AnonymousBoardService {

	@Autowired
    AnonymousBoardDao anonymousBoardtDao;
	
//  익명게시판 페이징
	public ArrayList<AnonymousBoardDto> getAnonymousBoardPagingList(BoardParams boardParams) {
		ArrayList<AnonymousBoardDto> list = anonymousBoardtDao.getAnonymousBoardPagingList(boardParams);
		return list;
	}

//	익명게시판 리스트 갯수
	public int anonymousBoardList(BoardParams boardParams) {
		return anonymousBoardtDao.anonymousBoardList(boardParams);
	}
	
	
//	익명게시판 추가
	public boolean insertAnonymousBoard(AnonymousBoardDto dto) {
		int len = anonymousBoardtDao.insertAnonymousBoard(dto);
		return len>0?true:false;
	}

	public AnonymousBoardDto anonymousBoardDetail(AnonymousBoardDto anonymousBoardDto) {
		anonymousBoardtDao.updateReadCount(anonymousBoardDto);
		return anonymousBoardtDao.anonymousBoardDetail(anonymousBoardDto);
	}

	public void anonymousBoardDelete(AnonymousBoardDto anonymousBoardDto) {
		anonymousBoardtDao.anonymousBoardDelete(anonymousBoardDto);
		
	}

	public void noticeInsertComment(CommentDto commentDto) {
		int ref = anonymousBoardtDao.getRef();
		commentDto.setRef(ref);
		anonymousBoardtDao.noticeInsertComment(commentDto);
		
	}

	public ArrayList<CommentDto> noticeComments(CommentDto commentDto) {
		return anonymousBoardtDao.noticeComments(commentDto);
	}

	public void noticeRealAnswerUpdate(CommentDto commentDto) {
		 anonymousBoardtDao.noticeRealAnswerUpdate(commentDto);
		
	}

	public void noticeAnswerDelete(CommentDto commentDto) {
		anonymousBoardtDao.noticeAnswerDelete(commentDto);
		
	}

	public void noticeAnswerInsert(CommentDto commentDto) {
		anonymousBoardtDao.updateCommentAnswer(commentDto);
		CommentDto dto = anonymousBoardtDao.selectRefStepDepth(commentDto);
		commentDto.setRef(dto.getRef());
		commentDto.setStep(dto.getRef()+1);
		commentDto.setDepth(dto.getDepth()+1);
		anonymousBoardtDao.noticeAnswerInsert(commentDto);
		
	}
	
}
