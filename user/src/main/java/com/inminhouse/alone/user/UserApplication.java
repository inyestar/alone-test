package com.inminhouse.alone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * 해당 애너테이션이 붙은 클래스가 스프링에서 정의하는 빈의 출처라고 스프링컨테이너에 지시
 * 서비스 초기화를 위한 핵심 로직은 이곳에 둔다.
 */
@SpringBootApplication
// 정의된 인터페이스로 REST 서비스를 호출하는데 사용되는 프록시 클래스를 스프링 클라우드가 동적으로 생성
@EnableFeignClients // netflix가 더이상 내부적으로 feign을 사용하지 않기로 결정하여 전체 코드를 openfeign 오픈소스로 넘김
// @EnableDiscoveryClient // 스프링 프레임워크가 관리하는 RestTemplate에 리본이 활성화된 인터셉터가 주입된다.
//@RefreshScope // 사용자 정의 스프링 프로퍼티만 재로딩하며 스프링 데이터에서 정의된 구성(데이터베이스정보)는 재로딩 안됨, 업데이트 수행을 위해
@EnableCircuitBreaker // 스프링 클라우드에 이 서비스에서 히스트릭스를 사용할 것이라고 지정한다.
public class UserApplication {

	/*
	 * 스프링 클라우드가 리본이 지원하는 RestTemplate 클래스를 생성하도록 지정 스프링 클라우드 초기에는 리본이 자동으로
	 * RestTemplate을 지원하였으나 이후 릴리즈에서 더이상 지원이 되지 않으므로 RestTemplate에서 리본을 사용하기 위해
	 * LoadBalanced 애너테이션을 직접 추가해야 한다.
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		/*
		 * 스프링 컨테이너 시작
		 */
		SpringApplication.run(UserApplication.class, args);
	}

}
