package com.inminhouse.alone.user.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.inminhouse.alone.user.model.Calendar;

/**
 * DiscoveryClient를 사용하기 위해서는 부트스트랩 클래스(UserApplication)에 EnableDiscoveryClients
 * 애너테이션을 붙여야 한다.
 * 
 * @author inye
 *
 */
@Component
public class CalendarDiscoveryClient {

	/*
	 * 리본 클라이언트의 부하 분산의 장점이나 URL을 직접 생성해야 하는 부담감이 있기때문에 등록된 서비스와 서비스 인스턴스를 알아야할 때만
	 * 해당 클라이언트를 사용할 것
	 */
	@Autowired
	private DiscoveryClient discoveryClient;

	public List<Calendar> getCalendar(String uid) {

		List<ServiceInstance> instances = discoveryClient.getInstances("calendar");
		if (instances.isEmpty()) {
			return null;
		}

		// RestTemplate을 autowired로 주입하지 않고 바로 인스턴스화하는 이유는
		// 부트스트랩 클래스에서 EnableDiscoveryClient 애너테이션을 붙이면 스프링 프레임워크가 관리하는 모든
		// RestTemplate에 리본이 활성화된 인터셉트가 주입되기때문에 DiscoveryClient만 이용하여 테스트 하기 위함
		ResponseEntity<Calendar[]> restExchange = new RestTemplate().exchange(String.format("%s/v1/alone/calendar/%s",
				instances.get(0)
					.getUri()
					.toString(),
				uid), HttpMethod.GET, null, Calendar[].class, uid);

		return Arrays.asList(restExchange.getBody());
	}

}
