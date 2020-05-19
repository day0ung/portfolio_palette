package com.palette.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.palette.dao.GroupDao;
import com.palette.dao.MemberCVDao;
import com.palette.model.BoardParams;
import com.palette.model.BoardReferenceDto;
import com.palette.model.CVReferenceDto;
import com.palette.model.GroupBoardDto;
import com.palette.model.MemberCVDto;
import com.palette.model.MemberDto;
import com.palette.s3.CVFile;
import com.palette.s3.ReferenceVo;
import com.palette.s3.S3Uploader;



@Service
@Transactional
public class MemberCVService {
	
	@Autowired
	private S3Uploader s3Uploader;
	
	@Autowired
	MemberCVDao memberCVDao;
	
	@Autowired
	GroupDao groupDao;
	
//	public boolean insertCV(MemberCVDto dto) {
//		int len = memberCVDao.insertCV(dto);
//		return len>0?true:false;
//	}
	public void insertCV(CVFile form) throws IOException {
		// board insert 먼저
		MemberCVDto memberCVDto = new MemberCVDto();
		memberCVDto.setCvSeq(form.getCvSeq());
		memberCVDto.setMemberSeq(form.getMemberSeq());
		memberCVDto.setTitle(form.getTitle());
		memberCVDto.setCategory(form.getCategory());
		memberCVDto.setWriteDate(form.getWriteDate());
		memberCVDto.setReadCount(form.getReadCount());
		memberCVDto.setFileName(form.getFileName());
		memberCVDto.setDbFileName(form.getDbFileName());
		memberCVDto.setDel(form.getDel());
		
		
		memberCVDao.insertCV(memberCVDto);
		// board Seq를 찾아서 다중파일 insert
		int cvSeq = memberCVDao.currCVSeq();
        for (MultipartFile file : form.getFiles()) {
			CVReferenceDto cvReferenceDto = new CVReferenceDto();
			cvReferenceDto.setFileName(file.getOriginalFilename());
			cvReferenceDto.setUrl(s3Uploader.upload(file, form.getMemberId()));
			cvReferenceDto.setMemberSeq(form.getMemberSeq());
			cvReferenceDto.setCvSeq(cvSeq);
            memberCVDao.insertCVReference(cvReferenceDto);
//		int boardSeq = groupDao.currBoardSeq();
//        for (MultipartFile file : form.getFiles()) {
//			BoardReferenceDto boardReferenceDto = new BoardReferenceDto();
//			boardReferenceDto.setFileName(file.getOriginalFilename());
//            boardReferenceDto.setUrl(s3Uploader.upload(file, form.getMemberId()));
//            boardReferenceDto.setMemberSeq(form.getMemberSeq());
//            boardReferenceDto.setBoardSeq(boardSeq);
//            groupDao.insertBoardReference(boardReferenceDto);
        }
	}
	
	public void CVNumChange(int seq) {
		memberCVDao.CVNumChange(seq);
	}
	
	public ArrayList<MemberCVDto> getCVPagingList(BoardParams boardParams){
		ArrayList<MemberCVDto> list = memberCVDao.getCVPagingList(boardParams);
		
		return list;
	}
	
	public int CVList(BoardParams boardParams) {
		return memberCVDao.CVList(boardParams);
	}
	
	public MemberCVDto getOneCV(int cvSeq) {
		return memberCVDao.getOneCV(cvSeq);
	}
	
	public void readCount(int cvSeq) {
		memberCVDao.readCount(cvSeq);
	}
	
	public MemberDto oneMember(int memberSeq) {
		return memberCVDao.oneMember(memberSeq);
	}
	
	public boolean updateCV(MemberCVDto dto){
		int len = memberCVDao.updateCV(dto);
		return len > 0?true:false;
	}
	
	public MemberCVDto getOneCVByMemberSeq(int memberSeq) {
		return memberCVDao.getOneCVByMemberSeq(memberSeq);
	}
	
	public CVReferenceDto cvDetailRef(int cvSeq) {
		return memberCVDao.cvDetailRef(cvSeq);
	}
	
	public CVReferenceDto cvDetailRefByMemberSeq(int memberSeq) {
		return memberCVDao.cvDetailRefByMemberSeq(memberSeq);
	}
}
