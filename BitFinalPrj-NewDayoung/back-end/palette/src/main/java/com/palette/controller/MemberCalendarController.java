package com.palette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.CalendarDto;
import com.palette.service.MemberCalendarService;

@CrossOrigin(origins = "*")
@RestController
public class MemberCalendarController {
	
	@Autowired
	MemberCalendarService service;
	
	@PostMapping(value="/getMemberCalendar")
	public List<CalendarDto> getMemberCalendar(CalendarDto dto){
		System.out.println("getMemberCalendar"+ dto.getMemberSeq());
		List<CalendarDto> list = service.getMemberCalendar(dto);
		System.out.println(list.toString());
		return list;
	}
	
	@GetMapping(value="/insertMemberCalendar")
	public String insertMemberCalendar(CalendarDto dto) {
		System.out.println("insertMemberCalendar++++"+dto.toString() );
		service.insertMemberCalendar(dto);
		return "";
	}
	
	@PostMapping(value="/deleteMemberCalendar")
	public String deleteMemberCalendar(CalendarDto dto) {
		System.out.println("deleteMemberCalendar"+ dto.toString());
		service.deleteMemberCalendar(dto);
		return "";
	}
	
	@GetMapping(value="/updateMemberCalendar")
	public String updateMemberCalendar(CalendarDto dto) {
		System.out.println("updateMemberCalendar"+ dto.toString());
		service.updateMemberCalendar(dto);
		return "";
	}
	@PostMapping(value="/updateDateCalendar")
	public String updateDateCalendar(CalendarDto dto) {
		System.out.println("updateDateCalendar"+ dto.toString());
		service.updateDateCalendar(dto);
		return "";
	}
	
}
