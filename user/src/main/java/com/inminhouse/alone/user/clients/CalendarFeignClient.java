package com.inminhouse.alone.user.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inminhouse.alone.user.model.Calendar;

@FeignClient("calendar") // 해당 애너테이션으로 캘린더 서비스를 feign에 확인
public interface CalendarFeignClient {

	@GetMapping(value = "/v1/alone/calendar/{uid}", consumes = "application/json")
	List<Calendar> getCalendar(@PathVariable("uid") String uid);

}
