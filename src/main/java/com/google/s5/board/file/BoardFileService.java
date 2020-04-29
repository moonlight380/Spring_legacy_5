package com.google.s5.board.file;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.s5.util.FileSaver;

@Service
public class BoardFileService {
	@Autowired
	private BoardFileDAO boardFileDAO;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileSaver fileSaver;
	
	
	
	public int fileDelete(String fileName) throws Exception{
		
		String path= servletContext.getRealPath("/resources/summer");
		int result=fileSaver.deleteFile(fileName, path); //숫자를 리턴해서 int
		
		return result;
	}
	
	
	public String fileInsert(MultipartFile files)throws Exception{
		String path = servletContext.getRealPath("/resources/summer");
		System.out.println(path);
		path = fileSaver.saveByTransfer(files, path);
		path = servletContext.getContextPath()+"/resources/summer/"+path;
		return path;
	}
	
	
	public BoardFileVO fileSelect(BoardFileVO boardFileVO) throws Exception{
		return boardFileDAO.fileSelect(boardFileVO);
	}

	//�뵒鍮꾩뿉�꽌留� 吏��� ->�뵒鍮꾩뿉�꽌 吏��슦湲� �쟾�뿉 �뙆�씪�룄 吏��슦硫� 醫뗫떎 留ㅺ컻蹂��닔濡� 媛숈씠 諛쏅뒗�떎.
	public int fileDelete(BoardFileVO boardFileVO) throws Exception{
		boardFileVO=boardFileDAO.fileSelect(boardFileVO);
		int result = boardFileDAO.fileDelete(boardFileVO);
		
		//1.癒쇱� HDD�뿉�꽌 吏��슦怨� 
		String board="uploadnotice";
		if(boardFileVO.getBoard()==2) {
			board="uploadqna";
		}
		String path= servletContext.getRealPath("/resources/"+board);
		
		fileSaver.deleteFile(boardFileVO.getFileName(), path);
		//�몮以묒뿉 �븯�굹�씪�룄 �떎�뙣�뻽�쓣 �븣 �뼱�뒓寃껋씠 臾몄젣媛� �엳�뒓�깘 
		// �뜑 以묒슂 �븳 寃� boardFileDAO.fileDelete(boardFileVO);
		return result;
	}
	

	
}
