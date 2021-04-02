package com.inminhouse.alone.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inminhouse.alone.user.form.UserForm;
import com.inminhouse.alone.user.model.User;
import com.inminhouse.alone.user.services.UserService;

/**
 * 서비스 앤드포인트 클래스 <br/>
 * - 데이터 교환 프로토콜 : HTTP <br/>
 * - POST, GET, PUT, DELETE <br/>
 * - 데이터 직렬화 형식 : JSON <br/>
 * - HTTP 상태 코드 리턴
 * 
 * @author inye
 *
 */
@RestController // REST 기반 서비스임을 명시하고 요청 및 응답을 json으로 자동 직렬 및 역직렬화 한다.
@RequestMapping(value = "/v1/alone/user") // 서비스의 HTTP 엔드포인트를 외부에 공개한다고 알림
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/{uid}")
	public User getUser(@PathVariable("uid") String uid) {

		return userService.getUser(uid)
			.orElseThrow();

	}

	@PutMapping(value = "/put")
	// XX: Entity 객체를 POJO로 바꾸는 것이 필요 데이터 인젝션 발생 위험 잇음
	public User putUser(UserForm requestedUser) {

		return userService.saveUser(requestedUser);
	}
}
