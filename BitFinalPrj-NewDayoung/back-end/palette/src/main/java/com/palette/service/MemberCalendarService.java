package com.palette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.MemberCalendarDao;
import com.palette.model.CalendarDto;

@Service
@Transactional
public class MemberCalendarService {
	
	@Autowired
	MemberCalendarDao dao;
	

	public List<CalendarDto> getMemberCalendar(CalendarDto dto){
		return dao.getMemberCalendar(dto);
	}
	
	public void insertMemberCalendar(CalendarDto dto) {
		 dao.insertMemberCalendar(dto);
	}
	
	public void deleteMemberCalendar(CalendarDto dto) {
		dao.deleteMemberCalendar(dto);
	}
	
	public void updateMemberCalendar(CalendarDto dto) {
		dao.updateMemberCalendar(dto);
	}
	
	public void updateDateCalendar(CalendarDto dto) {
		dao.updateDateCalendar(dto);
	}

}
