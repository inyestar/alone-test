package com.inminhouse.alone.calendar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inminhouse.alone.calendar.model.Calendar;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, String> {

	List<Calendar> findByUid(String uid);
}
