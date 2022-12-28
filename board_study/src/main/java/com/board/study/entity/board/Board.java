package com.board.study.entity.board;

import com.board.study.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entity 를 정의한다
// 테이블의 모든 필드와 Builder 생성자를 구현
// 만약 테이블명이 클래스명과 다를 경우 @Entity(name = "테이블명") 을 설정

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 생성자 생성
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@Entity // 실제 DB 의 테이블과 매칭될 클래스임을 명시
public class Board extends BaseTimeEntity {
	@Id // 해당 테이블의 PK 필드
	@GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
	private Long id;
	private String title;
	private String content;
	private int readCnt;
	private String registerId;
	
	@Builder // 어느 필드에 어떤 값을 채워야 할지 명확하게 정하여 생성 시점에 값을 채워줌
	public Board(Long id, String title, String content, int readCnt, String registerId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.registerId = registerId;
	}
}
