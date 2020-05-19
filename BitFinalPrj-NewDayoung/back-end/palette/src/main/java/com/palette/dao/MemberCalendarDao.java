package com.palette.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.CalendarDto;

@Mapper
@Repository
public interface MemberCalendarDao {
	
	List<CalendarDto> getMemberCalendar(CalendarDto dto);
	
	void insertMemberCalendar(CalendarDto dto);
	
	void deleteMemberCalendar(CalendarDto dto);
	
	void updateMemberCalendar(CalendarDto dto);
	
	void updateDateCalendar(CalendarDto dto);
}
