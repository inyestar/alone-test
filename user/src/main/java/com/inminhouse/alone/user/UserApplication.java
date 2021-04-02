package com.inminhouse.alone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/*
 * 해당 애너테이션이 붙은 클래스가 스프링에서 정의하는 빈의 출처라고 스프링컨테이너에 지시
 * 서비스 초기화를 위한 핵심 로직은 이곳에 둔다.
 */
@SpringBootApplication
@RefreshScope // 사용자 정의 스프링 프로퍼티만 재로딩하며 스프링 데이터에서 정의된 구성(데이터베이스정보)는 재로딩 안됨, 업데이트 수행을 위해
				// ~/actuator/refresh 엔드포이트 호출
public class UserApplication {

	public static void main(String[] args) {
		/*
		 * 스프링 컨테이너 시작
		 */
		SpringApplication.run(UserApplication.class, args);
	}

}
