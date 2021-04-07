package com.inminhouse.alone.user.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inminhouse.alone.user.clients.CalendarDiscoveryClient;
import com.inminhouse.alone.user.clients.CalendarFeignClient;
import com.inminhouse.alone.user.clients.CalendarRestTemplateClient;
import com.inminhouse.alone.user.form.UserForm;
import com.inminhouse.alone.user.model.Calendar;
import com.inminhouse.alone.user.model.User;
import com.inminhouse.alone.user.repository.UserRepository;

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
	public Optional<User> getUser(String uid) {
		return userRepository.findById(uid);
	}

	public User getUserWithClient(String uid, String clientType) {

		User user = userRepository.findById(uid)
			.orElse(User.builder()
				.build());

		List<Calendar> calendar = retrieveCalendar(uid, clientType);
		user.setCalendar(calendar.get(0));

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
