package com.google.s5.board.file;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.s5.util.FileSaver;

@Service
public class BoardFileService {
	@Autowired
	private BoardFileDAO boardFileDAO;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileSaver fileSaver;
	
	public BoardFileVO fileSelect(BoardFileVO boardFileVO) throws Exception{
		return boardFileDAO.fileSelect(boardFileVO);
	}

	//디비에서만 지움 ->디비에서 지우기 전에 파일도 지우면 좋다 매개변수로 같이 받는다.
	public int fileDelete(BoardFileVO boardFileVO) throws Exception{
		boardFileVO=boardFileDAO.fileSelect(boardFileVO);
		int result = boardFileDAO.fileDelete(boardFileVO);
		
		//1.먼저 HDD에서 지우고 
		String board="uploadnotice";
		if(boardFileVO.getBoard()==2) {
			board="uploadqna";
		}
		String path= servletContext.getRealPath("/resources/"+board);
		
		fileSaver.deleteFile(boardFileVO.getFileName(), path);
		//둘중에 하나라도 실패했을 때 어느것이 문제가 있느냐 
		// 더 중요 한 것 boardFileDAO.fileDelete(boardFileVO);
		return result;
	}
	

	
}
