package com.inminhouse.alone.user.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.inminhouse.alone.user.model.Calendar;

@Component
public class CalendarRestTemplateClient {

	@Autowired
	private RestTemplate restTemplate;

	// TODO: 리본이 가용 서비스 위치 풀에서 문제가 된 서비스 인스턴스를 제거하는지 테스트
	public List<Calendar> getCalendar(String uid) {
		return Arrays.asList(restTemplate
			// 유레카 서비스 id로 url 생성
			.exchange("http://calendar/v1/alone/calendar/{uid}", HttpMethod.GET, null, Calendar[].class, uid)
			.getBody());
	}

}
