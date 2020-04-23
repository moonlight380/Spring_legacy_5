package com.google.s5.notice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.s5.board.BoardVO;
import com.google.s5.board.file.BoardFileVO;

public class NoticeVO extends BoardVO{
	//노티스 브이오 하나당 보드파일 브이오 여러개 담을 수 있도록
	private List<BoardFileVO> boardFileVOs;

	public List<BoardFileVO> getBoardFileVOs() {
		return boardFileVOs;
	}

	public void setBoardFileVOs(List<BoardFileVO> boardFileVOs) {
		this.boardFileVOs = boardFileVOs;
	}
	
	//1.BoardVO를 멤버변수로
	//private BoardVO boardVO
	//2. BoardVO 상속 받기
	
//	private  MultipartFile[] files;
//
//	public MultipartFile[] getFiles() {
//		return files;
//	}
//
//	public void setFiles(MultipartFile[] files) {
//		this.files = files;
//	}
}
