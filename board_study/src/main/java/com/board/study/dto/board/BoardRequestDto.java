package com.board.study.dto.board;

import com.board.study.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 등록, 수정, 상세 조회에 필요한 필드 정의
// toEntity() 메소드는 Board Entity 를 Builder 하여 사용한다

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
	private Long id;
	private String title;
	private String content;
	private String registerId;
	
	public Board toEntity() {
		return Board.builder().title(title).content(content).registerId(registerId).build();
	}
}
