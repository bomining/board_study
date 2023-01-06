package com.board.study.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.dto.board.file.BoardFileRequestDto;
import com.board.study.dto.board.file.BoardFileResponseDto;
import com.board.study.service.BoardFileService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardFileController {
	private final BoardFileService boardFileService;
	
	@GetMapping("/file/download")
	public void downloadFile(@RequestParam() Long id, HttpServletResponse response) throws Exception {
		try {
//			파일 정보 조회
			BoardFileResponseDto fileInfo = boardFileService.findById(id);
			
			if (fileInfo == null) throw new FileNotFoundException("# Empty File data.");
			
//			경로와 파일명으로 파일 객체 생성
			File dFile = new File(fileInfo.getFilePath(), fileInfo.getSaveFileName());
			
//			파일 길이
			int fSize = (int) dFile.length();
			
//			파일 존재 시
			if (fSize > 0) {
//				파일명을 URLEncoder 하여 attachment, Content-Disposition Header 로 설정
				String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileInfo.getOrigFileName(), "UTF-8");
				
//				ContentType 설정
				response.setContentType("application/ectet-stream; charset=utf-8");
				
//				Header 설정
				response.setHeader("Content-Disposition", encodedFilename);
				
//				Content Length 설정
				response.setContentLength(fSize);
				
//				Java.io 의 가장 기본 파일 입출력 클래스
//				입출력 스트림을 생성,  버퍼를 사용하지 않기 때문에 느림
//				속도 문제를 해결하기 위해 버퍼를 사용하는 다른 클래스와 같이 쓰이는 경우가 多
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				
				bis = new BufferedInputStream(new FileInputStream(dFile));
				bos = new BufferedOutputStream(response.getOutputStream());
				
				try {
					byte[] buffer = new byte[4096];
					int bytesRead = 0;
					
//					모두 현재 파일의 포인터 위치를 기준으로
//					int read() : 1 byte 씩 내용을 읽어 정수로 반환
//					int read(byte[] b) : 파일 내용을 한번에 모두 읽어 배열에 저장
//					int read(byte[] b, int off, int len) : 'len' 길이만큼만 읽어 배열의 'off' 번째 위치부터 저장
					while ((bytesRead = bis.read(buffer)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					
//					버퍼에 남은 내용이 있다면 모두 파일에 출력
					bos.flush();
				} finally {
					bis.close();
					bos.close();
				}
			} else {
				throw new FileNotFoundException("# Empty File data.");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping("/file/delete.ajax")
	public String updateDeleteYn(Model model, BoardFileRequestDto boardFileRequestDto) throws Exception {
		try {
			model.addAttribute("result", boardFileService.updateDeleteYn(boardFileRequestDto.getIdArr()));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "jsonView";
	}
}
