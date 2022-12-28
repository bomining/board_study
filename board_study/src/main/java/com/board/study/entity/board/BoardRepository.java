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
	
	@Transactional // 선언적 트랜잭션 사용
	@Modifying // @Query 어노테이션으로 작성 된 변경, 삭제 쿼리를 사용할 때 사용
	@Query(value = UPDATE_BOARD, nativeQuery = true) // SQL 을 JPQL 로 작성 가능, nativeQuery = true 옵션으로 네이티브 쿼리도 사용 가능
	public int updateBoard(@Param("boardRequestDto") BoardRequestDto boardRequestDto);
}
