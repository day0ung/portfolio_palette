package com.palette.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

@Mapper
@Repository
public interface GroupDao {

	List<GroupDto> getAllGroup();
	ArrayList<GroupDto> getMyGroup(GroupParams groupParams);
	ArrayList<GroupDto> getMyOtherGroup(GroupParams groupParams);

	GroupDto getOneGroup(GroupDto insertDto);
	List<MemberDto> getGroupMember(GroupDto groupDto);
	
	List<GroupMemberDto> getGroupMemberName(int seq);

	ArrayList<GroupBoardDto> getgroupPagingList(BoardParams boardParams);
	ArrayList<GroupBoardDto> getgroupPdsList(int groupSeq);
	
	List<InterBigDto> getInterListAll();

	ArrayList<GroupDto> groupSearchList(GroupParams groupParams);
	
	int searchInterBigSeq(int interSmallSeq);
	void createGroup(GroupDto groupDto);
	int currGroupInfoSeq();
	void addSchedule(GroupSchedule groupSchedule);
	
	GroupBoardDto getGroupBoardDetail(int boardSeq);
	int getGroupBoardTotal(BoardParams boardParams);
	
	List<CalendarDto> getGroupCalendar(GroupDto groupDto);
	void insertGroupCalendar(CalendarDto calendarDto);
	void deleteGroupCalendar(int seq);
	void resizeCalendar(CalendarDto calendarDto);
	void updateGroupCalendar(CalendarDto calendarDto);
	
	void insertAttendGroup(GroupSchedule groupSchedule);
	int checkAttend(GroupSchedule groupSchedule);
	
	void groupBoardDelete(int boardSeq);
	void insertGroupBoard(GroupBoardDto groupBoardDto);
	void updateGroupBoard(GroupBoardDto groupBoardDto);
	void updateReadCount(int boardSeq);
	
	ArrayList<CommentDto> groupBoardDetailComments(int boardSeq);
	void insertComment(CommentDto commentDto);
	int getRef();
	void answerDelete(CommentDto commentDto);
	void answerUpdate(CommentDto commentDto);
	void updateCommentAnswer(CommentDto commentDto);
	CommentDto selectRefStepDepth(int boardCommentSeq);
	void insertCommentAnswer(CommentDto commentDto);
	

	int currBoardSeq();
	void insertBoardReference(BoardReferenceDto boardReferenceDto);
	
	List<BoardReferenceDto> getGroupReferenceDetailFileList(int boardSeq);

	List<BoardReferenceDto> getMypageReferenceList(int memberSeq);
	void groupReferenceDelete(int boardSeq);
	void joinGroupMemberRegistrationRequest(GroupMemberDto groupMemberDto);
	
	void likeGroupAdd(GroupMemberDto groupMemberDto);
	int checkLikeGroup(GroupMemberDto groupMemberDto);
	void likeGroupDelete(GroupMemberDto groupMemberDto);
	List<MemberLikeDto> getMylikeList(int memberSeq);
	GroupMemberDto checkGroupMember(GroupMemberDto groupMemberDto);
	void likeJoinGroupMemberRegistrationRequest(GroupMemberDto groupMemberDto);
	void groupWaitingDelete(GroupMemberDto groupMemberDto);
	void updateGroupMemberRegistrationRequest(GroupMemberDto groupMemberDto);

	void permissionGroupMember(GroupMemberDto groupMemberDto);
	void groupMemberDelete(GroupMemberDto groupMemberDto);
	ArrayList<GroupDto> getMyPremissionGroup(GroupParams groupParams);
	ArrayList<GroupDto> getMyPageCreatGroupList(GroupParams groupParams);
	void groupCreateCancle(GroupDto groupDto);
	



}
