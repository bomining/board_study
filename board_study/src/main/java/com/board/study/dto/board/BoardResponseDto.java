package com.board.study.dto.board;

import java.time.LocalDateTime;

import com.board.study.entity.board.Board;

import lombok.Getter;

// 게시물 목록, 상세 조회에 필요한 필드를 정의
// Board Entity 를 BoardResponseDto 에 맞게 변환하는 생성자 생성

@Getter
public class BoardResponseDto {
	private Long id;
	private String title;
	private String content;
	private int readCnt;
	private String registerId;
	private LocalDateTime registerTime;
	
	public BoardResponseDto(Board entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.readCnt = entity.getReadCnt();
		this.registerId = entity.getRegisterId();
		this.registerTime = entity.getRegisterTime();
	}
	
	@Override
	public String toString() {
		return "BoardListDto [id = " + id + 
				", title = " + title + 
				", content = " + content + 
				", readCnt = " + readCnt + 
				", registerId = " + registerId + 
				", registerTime = " + registerTime + 
				"]";
	}
}
