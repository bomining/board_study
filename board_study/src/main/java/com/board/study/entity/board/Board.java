package com.board.study.entity.board;

import com.board.study.entity.BaseTimeEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Entity 를 정의한다
// 테이블의 모든 필드와 Builder 생성자를 구현
// 만약 테이블명이 클래스명과 다를 경우 @Entity(name = "테이블명") 을 설정

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Board extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private int readCnt;
	private String registerId;
	
	@Builder
	public Board(Long id, String title, String content, int readCnt, String registerId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.registerId = registerId;
	}
}
