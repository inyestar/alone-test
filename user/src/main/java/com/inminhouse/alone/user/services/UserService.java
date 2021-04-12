package com.inminhouse.alone.user.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.inminhouse.alone.user.clients.CalendarDiscoveryClient;
import com.inminhouse.alone.user.clients.CalendarFeignClient;
import com.inminhouse.alone.user.clients.CalendarRestTemplateClient;
import com.inminhouse.alone.user.form.UserForm;
import com.inminhouse.alone.user.model.Calendar;
import com.inminhouse.alone.user.model.User;
import com.inminhouse.alone.user.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CalendarDiscoveryClient calendarDiscoveryClient;

	@Autowired
	private CalendarRestTemplateClient calendarRestTemplateClient;

	@Autowired
	private CalendarFeignClient calendarFeignClient;

	/**
	 * PUT / CREATE
	 * 
	 * @param user
	 */
	public User saveUser(UserForm requestUser) {

		// TODO: reuestedUser to user에 관련 통합 로직 필요
		return userRepository.save(User.builder()
			.uid(UUID.randomUUID()
				.toString())
			.email(requestUser.getEmail())
			.pw(requestUser.getPw())
			.name(requestUser.getName())
			.build());
	}

	/**
	 * GET / READ
	 * 
	 * @param uid
	 * @return
	 */
	@HystrixCommand // 메서드를 감싸는 프록시를 동정으로 생성, 원격 호출을 처리하기 위해 확보한 스레드가 있는 스레드 풀로 해당 메서드에 대한 모든 호출을 관리
	public Optional<User> getUser(String uid) {
		randomlyRunLong();
		return userRepository.findById(uid);
	}

	/**
	 * 무작위 타임아웃 생성
	 */
	private void randomlyRunLong() {
		Random random = new Random();
		int cnt = random.nextInt((3 - 1) + 1) + 1;
		if (cnt == 3) {
			try {
				Thread.sleep(11000); // 히스트릭스는 기본적으로 1초 후에 호출을 타임아웃 한다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public User getUserWithClient(String uid, String clientType) {

		User user = userRepository.findById(uid)
			.orElse(User.builder()
				.build());

		List<Calendar> calendar = retrieveCalendar(uid, clientType);
		user.setCalendar(CollectionUtils.isEmpty(calendar) ? Calendar.builder()
			.build() : calendar.get(0));

		return user;
	}

	private List<Calendar> retrieveCalendar(String uid, String clientType) {

		logger.info("Client [{}] activated", clientType);
		List<Calendar> calendar = null;
		switch (clientType) {
		case "feign":
			calendar = calendarFeignClient.getCalendar(uid);
			break;
		case "rest":
			calendar = calendarRestTemplateClient.getCalendar(uid);
			break;
		case "discovery":
			calendar = calendarDiscoveryClient.getCalendar(uid);
			break;
		default:
			logger.warn("No client specified so [rest] activated");

		}

		return calendar;
	}

	/**
	 * DELETE / DELETE
	 * 
	 * @param user
	 */
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
