package com.palette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.AdminGroupDao;
import com.palette.model.GroupDto;
import com.palette.model.MemberDto;

@Service
@Transactional
public class AdminGroupService {
	@Autowired
	AdminGroupDao admingroupdao;
	
	
	public List<GroupDto> selfOutputTest(){
		return admingroupdao.selfOutputTest();
	}
	
	public boolean acceptCreateGroup(GroupDto dto){
		return admingroupdao.acceptCreateGroup(dto);
	}

	public List<GroupDto> getAllStudyGroup(){
		return admingroupdao.getAllStudyGroup();
	}
	
	public GroupDto checkExistGroupMember(GroupDto dto){
        return admingroupdao.checkExistGroupMember(dto);
	}
	
	public boolean changeGroupMasterStatus(MemberDto dto){
		return admingroupdao.changeGroupMasterStatus(dto);
	}

	public boolean insertGroupLeader(GroupDto dto){
		return admingroupdao.insertGroupLeader(dto);
	}

	

}//end of adminMemberGroup class
