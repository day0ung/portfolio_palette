package com.palette.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.palette.dao.MemberBoardDao;
import com.palette.model.BoardReferenceDto;
import com.palette.model.MemberBoardDto;
import com.palette.s3.ReferenceVo;
import com.palette.s3.S3Uploader;

@Service
public class MemberBoardService {
	
	@Autowired
	MemberBoardDao dao;
	
	@Autowired
	private S3Uploader s3Uploader;
	
	public List<MemberBoardDto> getMemberBoard(MemberBoardDto dto){
		List<MemberBoardDto> list = dao.getMemberBoard(dto);
		return list;
	}
	
	public void uploadBoard(ReferenceVo form) throws IOException {
		//게시판 insert 싷행 먼저
		MemberBoardDto memberBoard = new MemberBoardDto();
		memberBoard.setMemberSeq(form.getMemberSeq());
		memberBoard.setTitle(form.getTitle());
		memberBoard.setContent(form.getContent());
		dao.writeBoard(memberBoard);
		int boardSeq = dao.currBoardSeq();
		//자료실 insert
		for (MultipartFile file : form.getFiles()) {
			BoardReferenceDto boardReferenceDto = new BoardReferenceDto();
			boardReferenceDto.setFileName(file.getOriginalFilename());
            boardReferenceDto.setUrl(s3Uploader.upload(file, form.getMemberId()) ); //접근할 수 있는  url
            boardReferenceDto.setMemberSeq(form.getMemberSeq());
            boardReferenceDto.setBoardSeq(boardSeq);
            dao.writeLibrary(boardReferenceDto);
        }
	}
	
	// ----------상세보기-----
	public MemberBoardDto detailLibarary(int boardSeq) {
		return dao.detailLibarary(boardSeq);
	}
	
	public List<BoardReferenceDto> detailFile(BoardReferenceDto dto) {
		return dao.detailFile(dto);
	}
	//----------------------
	
	
	public void deleteLibarary(int boardSeq) {
		dao.memberReferenceDelete(boardSeq);
	}
	
	public void updateLibarary(ReferenceVo form) throws IOException {
		MemberBoardDto memberBoard = new MemberBoardDto();
		memberBoard.setTitle(form.getTitle());
		memberBoard.setContent(form.getContent());
		memberBoard.setBoardSeq(form.getBoardSeq());
		dao.memberBoardUpdate(memberBoard);
		
		//기존 file del
		dao.memberReferenceDelete(form.getBoardSeq());
		//새로운 file insert
		for (MultipartFile file : form.getFiles()) {
			BoardReferenceDto boardReferenceDto = new BoardReferenceDto();
			boardReferenceDto.setFileName(file.getOriginalFilename());
            boardReferenceDto.setUrl(s3Uploader.upload(file, form.getMemberId()) ); //접근할 수 있는  url
            boardReferenceDto.setMemberSeq(form.getMemberSeq());
            boardReferenceDto.setBoardSeq(form.getBoardSeq());
            dao.writeLibrary(boardReferenceDto);
        }
		
	}
	
	public void memberBoardDel(int boardSeq) {
		dao.memberBoardDel(boardSeq);
	}
	
	
	
	
	

}
