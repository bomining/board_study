package com.board.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 어플리케이션 클래스에 @EnableJpaAuditing 을 추가해 Auditing 기능을 활성화

@EnableJpaAuditing
@SpringBootApplication
public class BoardStudyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BoardStudyApplication.class, args);
	}
}
