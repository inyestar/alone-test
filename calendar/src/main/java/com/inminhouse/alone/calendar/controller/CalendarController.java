package com.inminhouse.alone.calendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inminhouse.alone.calendar.model.Calendar;
import com.inminhouse.alone.calendar.services.CalendarService;

@RestController
@RequestMapping(value = "/v1/alone/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

//	@GetMapping(value = "/{cid}")
//	public Calendar getCalendar(@PathVariable("cid") String cid) {
//
//		return calendarService.getCalendar(cid)
//			.orElseThrow();
//	}

	@GetMapping(value = "/{uid}")
	public List<Calendar> getCalendarByUid(@PathVariable("uid") String uid) {
		return calendarService.getCalendarByUid(uid);
	}
}
