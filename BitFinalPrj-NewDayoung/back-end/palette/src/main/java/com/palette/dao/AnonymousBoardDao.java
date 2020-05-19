package com.palette.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.AnonymousBoardDto;
import com.palette.model.BoardParams;
import com.palette.model.CommentDto;

@Mapper
@Repository
public interface AnonymousBoardDao {
	
	ArrayList<AnonymousBoardDto> getAnonymousBoardPagingList(BoardParams boardParams);
	
	int anonymousBoardList(BoardParams boardParams);
	
	int insertAnonymousBoard(AnonymousBoardDto dto);

	AnonymousBoardDto anonymousBoardDetail(AnonymousBoardDto anonymousBoardDto);
	void updateReadCount(AnonymousBoardDto anonymousBoardDto);

	void anonymousBoardDelete(AnonymousBoardDto anonymousBoardDto);

//comment
	int getRef();
	void noticeInsertComment(CommentDto commentDto);

	ArrayList<CommentDto> noticeComments(CommentDto commentDto);

	void noticeRealAnswerUpdate(CommentDto commentDto);

	void noticeAnswerDelete(CommentDto commentDto);

	void updateCommentAnswer(CommentDto commentDto);

	CommentDto selectRefStepDepth(CommentDto commentDto);

	void noticeAnswerInsert(CommentDto commentDto);



}
