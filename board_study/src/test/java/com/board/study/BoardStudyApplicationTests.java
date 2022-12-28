package com.board.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.dto.board.BoardResponseDto;
import com.board.study.service.BoardService;

@SpringBootTest
class BoardStudyApplicationTests {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	void save() {
		BoardRequestDto boardSaveDto = new BoardRequestDto();
		
		boardSaveDto.setTitle("제목");
		boardSaveDto.setContent("내용");
		boardSaveDto.setRegisterId("작성자");
		
		Long result = boardService.save(boardSaveDto);
		
		if (result > 0) {
			System.out.println("# Success to save.");
//			findAll();
			findById(result);
		} else {
			System.out.println("# Failed to save.");
		}
	}

	/*
	 * void findAll() { List <BoardResponseDto> list = boardService.findAll();
	 * 
	 * if (list != null) { System.out.println("# Success to find all. : " +
	 * list.toString()); } else { System.out.println("# Failed to find all."); } }
	 */

	void findById(Long id) {
		BoardResponseDto info = boardService.findById(id);
		
		if (info != null) {
			System.out.println("# Success to find by id. : " + info.toString());
			updateBoard(id);
		} else {
			System.out.println("# Failed to find by id.");
		}
	}

	void updateBoard(Long id) {
		BoardRequestDto boardRequestDto = new BoardRequestDto();
		
		boardRequestDto.setId(id);
		boardRequestDto.setTitle("업데이트 제목");
		boardRequestDto.setContent("업데이트 내용");
		boardRequestDto.setRegisterId("작성자");
		
		int result = boardService.updateBoard(boardRequestDto);
		
		if (result > 0) {
			System.out.println("# Success to update board.");
		} else {
			System.out.println("# Failed to update board.");
		}
	}
}
