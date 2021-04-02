package com.inminhouse.alone.user.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inminhouse.alone.user.form.UserForm;
import com.inminhouse.alone.user.model.User;
import com.inminhouse.alone.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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

	/**
	 * DELETE / DELETE
	 * 
	 * @param user
	 */
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
