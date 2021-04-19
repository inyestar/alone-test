package com.inminhouse.alone.user.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.inminhouse.alone.user.model.Calendar;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class CalendarRestTemplateClient {

	@Autowired
	private RestTemplate restTemplate;

	// TODO: 리본이 가용 서비스 위치 풀에서 문제가 된 서비스 인스턴스를 제거하는지 테스트
	@HystrixCommand(fallbackMethod = "buildFallbackCalendar")
	public List<Calendar> getCalendar(String uid) {
		return Arrays.asList(restTemplate
			// 유레카 서비스 id로 url 생성
			.exchange("http://calendar/v1/calendar/{uid}", HttpMethod.GET, null, Calendar[].class, uid)
			.getBody());
	}

	/**
	 * calendar 서비스가 가용하지 않을 경우 fallback 사용될 메소드 <br/>
	 * fallback 메소드가 호출되는 이전 메소드의 형식과 동일해야 함 (파라미터, 리턴 갑)
	 * 
	 * @param uid
	 * @return
	 */
	@SuppressWarnings(value = { "unused" }) // refereced by Hystrix
	private List<Calendar> buildFallbackCalendar(String uid) {
		return Arrays.asList(Calendar.builder()
			.cid("NULL")
			.uid(uid)
			.event("No event available")
			.build());
	}

}
