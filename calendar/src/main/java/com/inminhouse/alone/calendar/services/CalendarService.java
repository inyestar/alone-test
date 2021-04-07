package com.inminhouse.alone.calendar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inminhouse.alone.calendar.model.Calendar;
import com.inminhouse.alone.calendar.repository.CalendarRepository;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;

	public Optional<Calendar> getCalendar(String cid) {

		return calendarRepository.findById(cid);
	}

	public List<Calendar> getCalendarByUid(String uid) {
		return calendarRepository.findByUid(uid);
	}

}
