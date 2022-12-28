package com.board.study.service;

import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.dto.board.BoardResponseDto;
import com.board.study.entity.board.Board;
import com.board.study.entity.board.BoardRepository;

import lombok.RequiredArgsConstructor;

// 게시판 기능을 담당할 서비스 클래스
// 파일 및 페이징 처리 추가 완료
// 현재 간단한 CRUD 메소드만 작성

// 2022-12-28
// 기존에 작성했던 서비스에서 페이징 처리 설정 추가
// PageRequest 객체로 간단하게 페이징 처리

@RequiredArgsConstructor // 초기화 되지 않은 모든 final 필드, @NonNull 로 마크되어 있는 모든 필드들에 대한 생성자를 자동 생성
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@Transactional // 선언적 트랜잭션 사용
	public Long save(BoardRequestDto boardSaveDto) {
		return boardRepository.save(boardSaveDto.toEntity()).getId();
	}
	
	/* 2022-12-28
	 * 트랜잭션에 readOnly = true 옵션을 주면 스프링 프레임워크가 하이버네이트 세션 플러시 모드를 MANUAL 로 설정한다. 이렇게
	 * 하면 강제로 플러시를 호출하지 않는 한 플러시가 일어나지 않는다. 따라서 트랜잭션을 커밋하더라도 영속성 컨텍스트가 플러시 되지 않아서
	 * 엔티티의 등록, 수정, 삭제가 동작하지 않고, 또한 읽기 전용으로 영속성 컨텍스트는 변겸 감지를 위한 스냅샷을 보관하지 않으므로 성능이
	 * 향상된다.
	 */
	
//	@Transactional(readOnly = true) // 선언적 트랜잭션 사용
//	public List <BoardResponseDto> findAll(Integer page, Integer size) {
//		return boardRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
//	}
	
//	2022-12-28 수정
	@Transactional(readOnly = true) // 선언적 트랜잭션 사용
	public HashMap<String, Object> findAll(Integer page, Integer size) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<Board> list = boardRepository.findAll(PageRequest.of(page, size));
		
		resultMap.put("list", list.stream().map(BoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());
		
		return resultMap;
	}
	
	public BoardResponseDto findById(Long id) {
		return new BoardResponseDto(boardRepository.findById(id).get());
	}
	
	public int updateBoard(BoardRequestDto boardRequestDto) {
		return boardRepository.updateBoard(boardRequestDto);
	}
	
	public int updateBoardReadCntInc(Long id) {
		return ((BoardService) boardRepository).updateBoardReadCntInc(id);
	}
	
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
}
