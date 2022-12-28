package com.board.study.dto.board;

import com.board.study.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 등록, 수정, 상세 조회에 필요한 필드 정의
// toEntity() 메소드는 Board Entity 를 Builder 하여 사용한다

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@Setter // 클래스 내 모든 필드의 Setter 메소드를 자동 생성
@NoArgsConstructor // 파라미터가 없는 생성자 생성
public class BoardRequestDto {
	private Long id;
	private String title;
	private String content;
	private String registerId;
	
	public Board toEntity() {
		return Board.builder().title(title).content(content).registerId(registerId).build();
	}
}
