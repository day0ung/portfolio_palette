package com.palette.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.palette.model.BoardParams;
import com.palette.model.BoardReferenceDto;
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
import com.palette.service.GroupService;


@CrossOrigin(origins = "*")
@RestController
public class GroupController {

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    GroupService groupService;

    // not-login
    @GetMapping(value = "/getAllGroup")
    public List<GroupDto> getAllGroup() {
    	System.out.println("getAllGroup 메소드 실행");
    	List<GroupDto> list = groupService.getAllGroup();
    	return list;
    }
    // login
    @PostMapping(value="/getMyGroup")
    public ArrayList<GroupDto> getMyGroup(GroupParams groupParams){
    	System.out.println("getMyGroup() 실행");
    	 ArrayList<GroupDto> list = groupService.getMyGroup(groupParams);
    	 return list;
    }
    @PostMapping(value="/getMyOtherGroup")
    public ArrayList<GroupDto> getMyOtherGroup(GroupParams groupParams){
    	System.out.println("getMyOtherGroup() 실행");

    	 ArrayList<GroupDto> list = groupService.getMyOtherGroup(groupParams);
    	 return list;
    }
    
    @PostMapping(value="/groupSearchList")
    public ArrayList<GroupDto> groupSearchList(GroupParams groupParams){
        ArrayList<GroupDto> list = null;
        if(groupParams.getInterBigSeq() == 0){
            list = (ArrayList)groupService.getAllGroup();
        }else{
            list = groupService.groupSearchList(groupParams);
        }
    	return list;
    }
    
    @PostMapping(value = "/getOneGroup")
    public GroupDto getOneGroup(GroupDto insertDto){
        System.out.println("getOneGroup()");
        GroupDto outDto = groupService.getOneGroup(insertDto);
    	return outDto;
    }
    @PostMapping(value = "/getGroupMember")
    public List<MemberDto> getGroupMember(GroupDto groupDto){
    	System.out.println("getGroupMember()");
    	List<MemberDto> list = groupService.getGroupMember(groupDto);
    	for (int i = 0; i < list.size(); i++) {
    		System.out.println("groupMember"+list.get(i));
			
		}
    	return list;
    }
    
    @PostMapping(value = "/creatGroupApply")
    public String creatGroupApply(GroupDto groupDto, GroupSchedule groupSchedule, MultipartFile file)
            throws IOException {
        System.out.println("creatGroupApply");
        groupDto.setInterBigSeq(groupService.searchInterBigSeq(groupDto.getInterSmallSeq()));
        if(file == null){
            groupDto.setImage("https://bit-palette.s3.ap-northeast-2.amazonaws.com/groupImage/1.png");
        }else{
            if(!file.isEmpty()){
                String imagePath = s3Uploader.upload(file, "groupImage");
                groupDto.setImage(imagePath);
            }
        }
        groupService.createGroup(groupDto);
        groupSchedule.setGroupInfoSeq(groupService.currGroupInfoSeq());
        groupService.addSchedule(groupSchedule);
        return "";
    }

    @PostMapping (value = "/permissionGroupMember")
    public String permissionGroupMember(GroupMemberDto groupMemberDto){
        System.out.println("permissionGroupMember()");
        groupService.permissionGroupMember(groupMemberDto);
        return "";
    }

    @PostMapping(value = "/groupMemberDelete")
    public String groupMemberDelete(GroupMemberDto groupMemberDto){
        System.out.println("groupMemberDelete()");
        groupService.groupMemberDelete(groupMemberDto);
        return "";
    }

    //TODO join like mypage
    @PostMapping(value = "/joinGroupMemberRegistrationRequest")
    public String joinGroupMemberRegistrationRequest(GroupMemberDto groupMemberDto){
        System.out.println("joinGroupMemberRegistrationRequest()");
        if(groupService.checkGroupMember(groupMemberDto).equals("가입된회원")){
            return "fail";
        }else if(groupService.checkGroupMember(groupMemberDto).equals("가입가능한회원")){
            groupService.joinGroupMemberRegistrationRequest(groupMemberDto);
            return "success";
        }else if(groupService.checkGroupMember(groupMemberDto).equals("가입대기중회원")){
            return "waiting";
        }else if(groupService.checkGroupMember(groupMemberDto).equals("업데이트해야하회원")){
            groupService.updateGroupMemberRegistrationRequest(groupMemberDto);
            return "success";
        }else{
            return "메소드 로직 실패";
        }
    }

    @PostMapping(value = "/likeGroupAdd")
    public String likeGroupAdd(GroupMemberDto groupMemberDto){
        System.out.println("likeGroupAdd()");
        groupService.likeGroupAdd(groupMemberDto);
        return "";
    }

    @PostMapping(value = "/getMylikeList")
    public List<MemberLikeDto> getMylikeList(int memberSeq){
        System.out.println("getMylikeList()");
        return  groupService.getMylikeList(memberSeq);
    }

    @PostMapping(value = "/likeGroupDelete")
    public String likeGroupDelete(GroupMemberDto groupMemberDto){
        System.out.println("likeGroupDelete()");
        groupService.likeGroupDelete(groupMemberDto);
        return "";
    }

    @PostMapping(value = "/groupWaitingDelete")
    public String groupWaitingDelete(GroupMemberDto groupMemberDto){
        System.out.println("groupWaitingDelete()");
        groupService.groupWaitingDelete(groupMemberDto);
        return "";
    }

    @PostMapping(value = "/getMyPremissionGroup")
    public ArrayList<GroupDto> getMyPremissionGroup(GroupParams groupParams){
        System.out.println("getMyPremissionGroup()");
        ArrayList<GroupDto> list = groupService.getMyPremissionGroup(groupParams);
        return list;
    }

    @PostMapping(value = "/getMyPageCreatGroupList")
    public ArrayList<GroupDto> getMyPageCreatGroupList(GroupParams groupParams){
        System.out.println("getMyPageCreatGroupList()");
        return groupService.getMyPageCreatGroupList(groupParams);
    }

    @PostMapping(value = "/groupCreateCancle")
    public String groupCreateCancle(GroupDto groupDto){
        System.out.println("groupCreateCancle()");
        groupService.groupCreateCancle(groupDto);
        return "";
    }
    
//TODO Board
    @PostMapping(value="/groupPagingList")
    public ArrayList<GroupBoardDto> getGroupPagingList(BoardParams boardParams) {
    	System.out.println("getGroupPagingList() : "+boardParams.toString());
    	boardParams.setStart( (boardParams.getPage()-1)*boardParams.getLimit() );
    	ArrayList<GroupBoardDto> list =	groupService.getGroupPagingList(boardParams);
    	System.out.println("list.size= "+list.size());
    	return list;
    }
    
    @PostMapping(value="/groupBoardTotal")
    public String getGroupBoardTotal(BoardParams boardParams) {
    	System.out.println("getGroupBoardTotal()" + boardParams.toString());
        int total = groupService.getGroupBoardTotal(boardParams);
        String stotal = total +"";
    	System.out.println("total: "+total);
    	return stotal;
    }
    
    @PostMapping(value="/groupBoardDetail")
    public GroupBoardDto getGroupBoardDetail(int boardSeq) {
        System.out.println("getGroupBoardDetail" + boardSeq);
        groupService.updateReadCount(boardSeq);
        return groupService.getGroupBoardDetail(boardSeq);
    }

    @PostMapping(value = "/groupBoardDelete")
    public String groupBoardDelete(int boardSeq){
        System.out.println("groupBoardDelete()");
        groupService.groupBoardDelete(boardSeq);
        return "";
    }

    @GetMapping(value = "/insertGroupBoard")
    public String insertGroupBoard(GroupBoardDto groupBoardDto){
        System.out.println("insertGroupBoard()");
        groupService.insertGroupBoard(groupBoardDto);
        return "";
    }

    @GetMapping(value = "/updateGroupBoard")
    public String updateGroupBoard(GroupBoardDto groupBoardDto){
        System.out.println("updateGroupBoard()" + groupBoardDto.toString());
        groupService.updateGroupBoard(groupBoardDto);
        return "";
    }
    

    
//TODO Reference
    @PostMapping(value="/groupPdsList")
    public ArrayList<GroupBoardDto> getgroupPdsList(int groupSeq) {
    	System.out.println("getgroupPdsList");
        ArrayList<GroupBoardDto> list =	groupService.getgroupPdsList(groupSeq);
        return list;
    }
    
    @GetMapping(value="/getGroupMemberName")
    public List<GroupMemberDto> getGroupMemberName(int interGroupSeq){
    	System.out.println("getGroupMemberName()");
    	List<GroupMemberDto> list = groupService.getGroupMemberName(interGroupSeq);
    	return list;
    }
    
    @GetMapping(value="/getInterListAll")
    public List<InterBigDto> getInterListAll() {
        System.out.println("getInterListAll()");
        List<InterBigDto> list = groupService.getInterListAll();
        return list;
    }

    @PostMapping(value = "/insertGroupReference")
    public String insertGroupReference(ReferenceVo form) throws IOException{
        groupService.insertBoardReference(form);
        return "";
    }

    @PostMapping(value = "/groupReferenceDetailFileList")
    public List<BoardReferenceDto> getGroupReferenceDetailFileList(int boardSeq){
        System.out.println("getGroupReferenceDetailFileList()" + boardSeq);
        return groupService.getGroupReferenceDetailFileList(boardSeq);
    }

    @PostMapping(value = "/MypageReferenceList")
    public List<BoardReferenceDto> getMypageReferenceList(int memberSeq){
        System.out.println("getMypageReferenceList()");
        return groupService.getMypageReferenceList(memberSeq);
    }

    @PostMapping(value = "/groupReferenceDelete")
    public String groupReferenceDelete(int boardSeq){
        System.out.println("groupReferenceDelete()");
        groupService.groupReferenceDelete(boardSeq);
        return "";
    }

    @PostMapping(value = "/updateGroupReference")
    public String updateGroupReference(ReferenceVo form) throws IOException {
        System.out.println("updateGroupReference()");
        groupService.updateGroupReference(form);
        return "";
    }

//TODO Comment
    @PostMapping(value="/groupBoardDetailComments")
    public ArrayList<CommentDto> getGroupBoardDetailComments(int boardSeq) {
        System.out.println("getGroupBoardDetailComments() : " + boardSeq);
        ArrayList<CommentDto> list = groupService.groupBoardDetailComments(boardSeq);
        return list;
    }
    @PostMapping(value = "/insertComment")
    public String insertComment(CommentDto commentDto) {
    	System.out.println("insertComment(): "+commentDto.toString());
    	groupService.insertComment(commentDto);
    	return "";
    }

    @PostMapping(value = "/answerDelete")
    public String answerDelete(CommentDto commentDto) {
    	System.out.println("answerDelete()");
    	groupService.answerDelete(commentDto);
    	return "";
    }
    
    @PostMapping(value = "/realAnswerUpdate")
    public String answerUpdate(CommentDto commentDto) {
    	System.out.println("answerUpdate()");
    	groupService.answerUpdate(commentDto);
    	return "";
    }
    
    @PostMapping(value = "/answerInsert")
    public String answerInsert(CommentDto commentDto) {
    	System.out.println("answerInsert()" +commentDto.toString());
    	groupService.answerInsert(commentDto);
    	return "";
    }
    
    
//TODO attendance
    @PostMapping(value="/attendGroup")
    public String getAttendGroup(String checkday, GroupSchedule groupSchedule) {
    	System.out.println("getAttendGroup() " +checkday +"/" + groupSchedule.toString());
    	
    	int result = groupService.getAttendGroup(checkday, groupSchedule);

    	String s_result= result+"";
    	    	
    	return s_result;
    }
	@PostMapping(value="/getAttendStatus")
	public String checkAttend(GroupSchedule groupSchedule) {
		System.out.println("getAttendStatus()");
		
		int result = groupService.checkAttend(groupSchedule);
		
		String s_result = result+"";
		
		return s_result;
		
	}
}
