package com.board.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.dto.board.BoardRequestDto;
import com.board.study.service.BoardService;

import lombok.RequiredArgsConstructor;

// 목록, 등록, 상세 화면 맵핑, 등록 액션 메소드 생성
// 목록에는 페이징 처리를 위한 파라미터를 받음

@RequiredArgsConstructor // 초기화 되지 않은 모든 final 필드, @NonNull 로 마크되어 있는 모든 필드들에 대한 생성자를 자동 생성
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/list")
	public String getBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page, 
			@RequestParam(required = false, defaultValue = "5") Integer size) throws Exception {
		
		try { 
			model.addAttribute("resultMap", boardService.findAll(page, size));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/board/list";
	}
	
	@GetMapping("/board/write")
	public String getBoardWritePage(Model model, BoardRequestDto boardRequestDto) {
		return "/board/write";
	}
	
	@GetMapping("/board/view")
	public String getBoardViewPage(Model model, BoardRequestDto boardRequestDto) throws Exception {
		try {
			if (boardRequestDto.getId() != null) {
				model.addAttribute("info", boardService.findById(boardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/board/view";
	}
	
	@PostMapping("/board/write/action")
	public String boardWriteAction(Model model, BoardRequestDto boardRequestDto) throws Exception {
		try {
			Long result = boardService.save(boardRequestDto);
			
			if (result < 0) {
				throw new Exception("# Exception boardWriteAction.");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}
	
//	2023-01-02 상세 화면 수정, 상세 화면 내에서 삭제, 목록에서 삭제에 대한 서비스 호출 처리
	
	@PostMapping("/board/view/action")
	public String boardViewAction(Model model, BoardRequestDto boardRequestDto) throws Exception {
		try {
			int result = boardService.updateBoard(boardRequestDto);
			if (result < 1) {
				throw new Exception("# Exception boardViewAction.");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/board/view/delete")
	public String boardViewDeleteAction(Model model, @RequestParam() Long id) throws Exception {
		try {
			boardService.deleteById(id);
		} catch (Exception e) {  
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/board/delete")
	public String boardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {
		try {
			boardService.deleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/board/list";
	}
}
