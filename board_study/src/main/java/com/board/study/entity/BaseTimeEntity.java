package com.board.study.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// Entity 에서 공통적으로 사용될 날짜 필드를 관리할 클래스 정의
// 꼭 날짜가 아니더라도 공통적으로 반복되는 필드를 정의하여 사용해도 됨

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@MappedSuperclass // 이 클래스를 상속하는 엔티티에 맵핑되는 테이블에 생성
@EntityListeners(AuditingEntityListener.class) // JPA 내부에서 엔티티 객체가 생성 / 변경 되는 것을 감지하는 역할
public class BaseTimeEntity {

		@CreatedDate // JPA 에서 엔티티의 생성 시간 처리
		private LocalDateTime registerTime;
		
		@LastModifiedDate // 최종 수정 시간을 자동 처리
		private LocalDateTime updateTime;
}
