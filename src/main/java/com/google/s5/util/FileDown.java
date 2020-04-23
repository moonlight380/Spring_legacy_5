package com.google.s5.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.google.s5.board.file.BoardFileVO;

@Component
public class FileDown extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		BoardFileVO boardFileVO=(BoardFileVO)model.get("file");
		
		String fileName = boardFileVO.getFileName();
		String path ="";
		if(boardFileVO.getBoard()==1) {
			path="/resources/uploadnotice";
		}else {
			path="/resources/uploadqna";
		}
		//작은범위에서 큰범위를 꺼낼 수 잇음
		path = request.getSession().getServletContext().getRealPath(path);
		
		//1. HDD에서 파일을 읽을 준비
		File file = new File(path, fileName);
		
		//2. 파일의 크기
			response.setContentLength((int)file.length());
				
		//3. 다운로드시 파일 이름 인코딩
			String downName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");
				
		//4. header 설정
			response.setHeader("Content-disposition", "attachment;filename=\""+downName+"\"");
			response.setHeader("Content-transfer-Encoding", "binary");
		
		//5.Stream 연결 후 전송
			FileInputStream fi = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
		
		//클라이언트 pc와 연결된 os
		//파일업로드 할 때 썼던 메소드
			FileCopyUtils.copy(fi, os);
		
		System.out.println("in");
		//6.자원해제
		os.close();
		fi.close();
		
	}

}
