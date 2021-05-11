package com.inminhouse.alone.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 서비스를 zuul 서버로 사용한다.
// zuul은 리버스 프록시 (클라이언트 -> 인터넷 -> 리버스프록시 -> 서버) <> 포워드프록시 (클라리언트 -> 포워드프록시 -> 인터넷 -> 서버)
// zuul은 서비스 ID로 서비스를 찾은 후 넷플릭스 리본으로 주울 내부에서 요청에 대한 클라이언트 측 부하 분산을 수행한다.
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
