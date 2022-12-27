package com.board.study.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.board.study.dto.board.BoardRequestDto;

import jakarta.transaction.Transactional;

// JpaRepository 를 상속받아 CRUD 의 기능을 담당하는 인터페이스
// @Query 를 사용한 JPQL 방식의 updateBoard() 메소드 구현
// 이 방식으로 쿼리를 직접 작성하여 사용 가능

public interface BoardRepository extends JpaRepository<Board, Long> {
	String UPDATE_BOARD = "UPDATE Board " + 
			"SET TITLE = :#{#boardRequestDto.title}, " + 
			"CONTENT = :#{#boardRequestDto.content}, " + 
			"UPDATE_TIME = NOW() " + 
			"WHERE ID = :#{#boardRequestDto.id}";
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD, nativeQuery = true)
	public int updateBoard(@Param("boardRequestDto") BoardRequestDto boardRequestDto);
}
