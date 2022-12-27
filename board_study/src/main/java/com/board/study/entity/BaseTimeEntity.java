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

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

		@CreatedDate
		private LocalDateTime registerTime;
		
		@LastModifiedDate
		private LocalDateTime updateTime;
}