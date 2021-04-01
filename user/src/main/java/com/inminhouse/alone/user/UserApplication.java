package com.inminhouse.alone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 해당 애너테이션이 붙은 클래스가 스프링에서 정의하는 빈의 출처라고 스프링컨테이너에 지시
 * 서비스 초기화를 위한 핵심 로직은 이곳에 둔다.
 */
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		/*
		 * 스프링 컨테이너 시작
		 */
		SpringApplication.run(UserApplication.class, args);
	}

}
