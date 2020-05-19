package com.palette.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.palette.dao.GroupDao;
import com.palette.model.BoardParams;
import com.palette.model.BoardReferenceDto;
import com.palette.model.CalendarDto;
import com.palette.model.CommentDto;
import com.palette.model.GroupBoardDto;
import com.palette.model.GroupDto;
import com.palette.model.GroupMemberDto;
import com.palette.model.GroupParams;
import com.palette.model.GroupSchedule;
import com.palette.model.InterBigDto;
import com.palette.model.MemberDto;
import com.palette.model.MemberLikeDto;
import com.palette.s3.ReferenceVo;
import com.palette.s3.S3Uploader;

@Service
@Transactional
public class GroupService {

	@Autowired
	private S3Uploader s3Uploader;

	@Autowired
	GroupDao groupDao;

// TODO: GROUP LIST
	public List<GroupDto> getAllGroup() {
		return groupDao.getAllGroup();
	}

	public ArrayList<GroupDto> getMyGroup(GroupParams groupParams) {
		return groupDao.getMyGroup(groupParams);
	}

	public ArrayList<GroupDto> getMyOtherGroup(GroupParams groupParams) {
		return groupDao.getMyOtherGroup(groupParams);
	}

	public ArrayList<GroupDto> groupSearchList(GroupParams groupParams) {
		return groupDao.groupSearchList(groupParams);
	}

	public GroupDto getOneGroup(GroupDto insertDto) {
		return groupDao.getOneGroup(insertDto);
	}
	public List<MemberDto> getGroupMember(GroupDto groupDto) {
		return groupDao.getGroupMember(groupDto);
	}

	public List<GroupMemberDto> getGroupMemberName(int seq) {
		return groupDao.getGroupMemberName(seq);
	}

	public List<InterBigDto> getInterListAll() {
		return groupDao.getInterListAll();
	}

// TODO: CREATE GROUP
	public void createGroup(GroupDto groupDto) {
		groupDao.createGroup(groupDto);
	}

	public void addSchedule(GroupSchedule groupSchedule) {
		groupDao.addSchedule(groupSchedule);
	}

	public int currGroupInfoSeq() {
		return groupDao.currGroupInfoSeq();
	}

	public int searchInterBigSeq(int interSmallSeq) {
		return groupDao.searchInterBigSeq(interSmallSeq);
	}

	public GroupBoardDto getGroupBoardDetail(int boardSeq) {
		return groupDao.getGroupBoardDetail(boardSeq);
	}

	public int getGroupBoardTotal(BoardParams boardParams) {

		return groupDao.getGroupBoardTotal(boardParams);
	}

// TODO: GROUP CALENDAR
	public List<CalendarDto> getGroupCalendar(GroupDto groupDto) {
		return groupDao.getGroupCalendar(groupDto);
	}

	public void insertGroupCalendar(CalendarDto calendarDto) {
		groupDao.insertGroupCalendar(calendarDto);
	}
	public void deleteGroupCalendar(int seq) {
		groupDao.deleteGroupCalendar(seq);
	}
	public void resizeCalendar(CalendarDto calendarDto) {
		groupDao.resizeCalendar(calendarDto);
	}
	public void updateGroupCalendar(CalendarDto calendarDto) {
		groupDao.updateGroupCalendar(calendarDto);
	}
	
// TODO: GROUP ATTENDENCE	
	public int getAttendGroup(String checkday, GroupSchedule groupSchedule) {
		int a = -1;
		if (checkday.equals("Sunday")) {
			a = groupSchedule.getSunday();
		}
		if (checkday.equals("Monday")) {
			a = groupSchedule.getMonday();
		}
		if (checkday.equals("Tuesday")) {
			a = groupSchedule.getTuesday();
		}
		if (checkday.equals("Wednesday")) {
			a = groupSchedule.getWednesday();
		}
		if (checkday.equals("Thursday")) {
			a = groupSchedule.getThursday();
		}
		if (checkday.equals("Friday")) {
			a = groupSchedule.getFriday();
		}
		if (checkday.equals("Saturday")) {
			a = groupSchedule.getSaturday();
		}

		if (a == 1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c1 = Calendar.getInstance();
			String strToday = sdf.format(c1.getTime());
			System.out.println("strToday");
			groupSchedule.setNowDate(strToday);

			int b = groupDao.checkAttend(groupSchedule);

			if (b == 1) { // 이미 출석처리됨
				a = 2;
			} else {
				groupDao.insertAttendGroup(groupSchedule);
			}
		}
		return a;
	}

	public int checkAttend(GroupSchedule groupSchedule) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		System.out.println(strToday);
		groupSchedule.setNowDate(strToday);
		return groupDao.checkAttend(groupSchedule);
	}

// TODO: GROUP BOARD
	public ArrayList<GroupBoardDto> getGroupPagingList(BoardParams boardParams) {
		return groupDao.getgroupPagingList(boardParams);
	}

	public void groupBoardDelete(int boardSeq) {
		groupDao.groupBoardDelete(boardSeq);
	}

	public void insertGroupBoard(GroupBoardDto groupBoardDto) {
		groupBoardDto.setBoardAuth(2);
		groupDao.insertGroupBoard(groupBoardDto);
	}

	public void updateGroupBoard(GroupBoardDto groupBoardDto) {
		groupDao.updateGroupBoard(groupBoardDto);
	}

	public void updateReadCount(int boardSeq) {
		groupDao.updateReadCount(boardSeq);
	}
	
// TODO: GROUP PDS BOARD
	public ArrayList<GroupBoardDto> getgroupPdsList(int groupSeq) {
		return groupDao.getgroupPdsList(groupSeq);
	}

	public void insertBoardReference(ReferenceVo form) throws IOException {
		// board insert 먼저
		GroupBoardDto boardDto = new GroupBoardDto();
		boardDto.setGroupInfoSeq(form.getGroupInfoSeq());
		boardDto.setMemberSeq(form.getMemberSeq());
		boardDto.setCategory(2);
		boardDto.setTitle(form.getTitle());
		boardDto.setContent(form.getContent());
		boardDto.setBoardAuth(2);
		groupDao.insertGroupBoard(boardDto);
		// board Seq를 찾아서 다중파일 insert
		int boardSeq = groupDao.currBoardSeq();
        for (MultipartFile file : form.getFiles()) {
			BoardReferenceDto boardReferenceDto = new BoardReferenceDto();
			boardReferenceDto.setFileName(file.getOriginalFilename());
            boardReferenceDto.setUrl(s3Uploader.upload(file, form.getMemberId())); //접근할 수 있는  url
            boardReferenceDto.setMemberSeq(form.getMemberSeq());
            boardReferenceDto.setBoardSeq(boardSeq);
            groupDao.insertBoardReference(boardReferenceDto);
        }
	}
	public void updateGroupReference(ReferenceVo form) throws IOException {
		// board update 먼저실행
		GroupBoardDto boardDto = new GroupBoardDto();
		boardDto.setBoardSeq(form.getBoardSeq());
		boardDto.setTitle(form.getTitle());
		boardDto.setContent(form.getContent());
		groupDao.updateGroupBoard(boardDto);
		// 기존 file delete 먼저실행후
		groupDao.groupReferenceDelete(form.getBoardSeq());
		// 같은 boardSeq로 file upload
		for(MultipartFile file : form.getFiles()){
			BoardReferenceDto boardReferenceDto = new BoardReferenceDto();
			boardReferenceDto.setFileName(file.getOriginalFilename());
			boardReferenceDto.setUrl(s3Uploader.upload(file, form.getMemberId()));
			boardReferenceDto.setMemberSeq(form.getMemberSeq());
			boardReferenceDto.setBoardSeq(form.getBoardSeq());
			groupDao.insertBoardReference(boardReferenceDto);
		}
	}
//TODO Comment
	public ArrayList<CommentDto> groupBoardDetailComments(int boardSeq) {
		return groupDao.groupBoardDetailComments(boardSeq);
	}
	public void insertComment(CommentDto commentDto) {
		int ref = groupDao.getRef();
		commentDto.setRef(ref);
		groupDao.insertComment(commentDto);
	}
	public void answerDelete(CommentDto commentDto) {
		groupDao.answerDelete(commentDto);
	}
	public void answerUpdate(CommentDto commentDto) {
		groupDao.answerUpdate(commentDto);
	}
	public void answerInsert(CommentDto commentDto) {
		groupDao.updateCommentAnswer(commentDto);
		CommentDto dto = groupDao.selectRefStepDepth(commentDto.getBoardCommentSeq());
		commentDto.setRef(dto.getRef());
		commentDto.setStep(dto.getStep()+1);
		commentDto.setDepth(dto.getDepth()+1);
		
		System.out.println("answerInsert Service: " + commentDto.toString());
		groupDao.insertCommentAnswer(commentDto);
		
	}
	
	
	public List<BoardReferenceDto> getGroupReferenceDetailFileList(int boardSeq) {
		return groupDao.getGroupReferenceDetailFileList(boardSeq);
	}
	public List<BoardReferenceDto> getMypageReferenceList(int memberSeq) {
		return groupDao.getMypageReferenceList(memberSeq);
	}

	public void groupReferenceDelete(int boardSeq) {
		groupDao.groupBoardDelete(boardSeq);
		groupDao.groupReferenceDelete(boardSeq);
	}

	public void joinGroupMemberRegistrationRequest(GroupMemberDto groupMemberDto) {
		groupDao.joinGroupMemberRegistrationRequest(groupMemberDto);
	}

	public void likeGroupAdd(GroupMemberDto groupMemberDto) {
		if(groupDao.checkLikeGroup(groupMemberDto) == 0){
			groupDao.likeGroupAdd(groupMemberDto);
			groupDao.likeJoinGroupMemberRegistrationRequest(groupMemberDto);
		}
	}

	public List<MemberLikeDto> getMylikeList(int memberSeq) {
		return groupDao.getMylikeList(memberSeq);
	}

	public String checkGroupMember(GroupMemberDto groupMemberDto) {
		if (groupDao.checkGroupMember(groupMemberDto) == null){
			return "가입가능한회원";
		}else{
			if(groupDao.checkGroupMember(groupMemberDto).getDel() == 0){
					return "가입된회원";
			}else if(groupDao.checkGroupMember(groupMemberDto).getDel() == 1){
				return "가입가능한회원";
			}else if(groupDao.checkGroupMember(groupMemberDto).getDel() == 2){
				return "가입대기중회원";
			}else if(groupDao.checkGroupMember(groupMemberDto).getDel() == 3){
				return "업데이트해야하회원";
			}else{
				return "가입가능한회원";
			}
		}
	}

	public void likeGroupDelete(GroupMemberDto groupMemberDto) {
		groupDao.likeGroupDelete(groupMemberDto);
	}

	public void groupWaitingDelete(GroupMemberDto groupMemberDto) {
		groupDao.groupWaitingDelete(groupMemberDto);
	}

	public void updateGroupMemberRegistrationRequest(GroupMemberDto groupMemberDto) {
		groupDao.updateGroupMemberRegistrationRequest(groupMemberDto);
	}

	public void permissionGroupMember(GroupMemberDto groupMemberDto) {
		groupDao.permissionGroupMember(groupMemberDto);
	}

	public void groupMemberDelete(GroupMemberDto groupMemberDto) {
		groupDao.groupMemberDelete(groupMemberDto);
	}

	public ArrayList<GroupDto> getMyPremissionGroup(GroupParams groupParams) {
		return groupDao.getMyPremissionGroup(groupParams);
	}

	public ArrayList<GroupDto> getMyPageCreatGroupList(GroupParams groupParams) {
		return groupDao.getMyPageCreatGroupList(groupParams);
	}

	public void groupCreateCancle(GroupDto groupDto) {
		groupDao.groupCreateCancle(groupDto);
	}

	

}
