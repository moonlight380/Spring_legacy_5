package com.google.s5.notice;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.s5.board.BoardService;
import com.google.s5.board.BoardVO;
import com.google.s5.board.file.BoardFileDAO;
import com.google.s5.board.file.BoardFileVO;
import com.google.s5.util.FileSaver;
import com.google.s5.util.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO; //서비스는 디에오에 대해 의존적	
	@Autowired // 만들어진 객체는 어딘가에 있고 객체를 주입한다는 것
	private FileSaver fileSaver;// 만들어질 파일세이버를 생각하며 만듦, 안만들어질 수도 있음: 에러
	//객체의 생명주기를 관리하는 것이 container임
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private BoardFileDAO boardFileDAO;
	
	//List
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		
		//int startRow =(pager.-1)*10+1;
		//int lastRow= curPage*10;
		//Map<String,Integer> map = new HashMap<String, Integer>();
		//map.put("startRow", startRow);
		//map.put("lastRow", lastRow);
		
		pager.makeRow();
		
		//--------------------------------------------
		long totalCount= noticeDAO.boardCount(pager);
		pager.makePage(totalCount);
		//리턴을 안해도 totalCount가 controller에 가는데 왜 그럴까?
		//참조변수는 주소를 남기는데 이미 주소 안에  totalCount가 있기 때문
		return noticeDAO.boardList(pager);
	}
	//select
	@Override
	public BoardVO boardSelect(long num) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.boardSelect(num);
		return noticeDAO.boardSelect(num);
	}
	//write
	@Override
	public int boardWrite(BoardVO boardVO,MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub
		String path = servletContext.getRealPath("/resources/uploadnotice");
		//String path = session.getServletContext().getRealPath("/resources/noticeUpload");
		System.out.println(path);
		
		
		//시퀀스 번호 받기( dual로 맵퍼에서 가져옴
		boardVO.setNum(noticeDAO.boardNum());
		
		//noticeDAO table insert
		int result=noticeDAO.boardWrite(boardVO);
		
		
		for(MultipartFile file:files) {
			if(file.getSize()>0) {
			BoardFileVO boardFileVO = new BoardFileVO(); // 한번 돌때마다 새로운 파일
			String fileName=fileSaver.saveByTransfer(file, path);
			boardFileVO.setNum(boardVO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(1);
			
			boardFileDAO.fileInsert(boardFileVO); //파일의 갯수만큼이라서 반복문 안에
			}
		}
		return result;//noticeDAO.boardWrite(boardVO);
	}
		
	//파일 수정하고 다시 올리기
	@Override
	public int boardupdate(BoardVO boardVO,MultipartFile[] files) throws Exception {
		//HDD file save
		String path = servletContext.getRealPath("/resources/uploadnotice");

		int result = noticeDAO.boardUpdate(boardVO);
		for(MultipartFile multipartFile:files) {
			String name=fileSaver.saveByUtils(multipartFile, path);
			System.out.println(name);
			
			if(multipartFile.getSize()>0) {
			BoardFileVO boardFileVO = new BoardFileVO(); // 한번 돌때마다 새로운 파일
			
			boardFileVO.setFileName(fileSaver.saveByTransfer(multipartFile, path));
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
		
			boardFileVO.setBoard(1);
			
			result=boardFileDAO.fileInsert(boardFileVO); //파일의 갯수만큼이라서 반복문 안에// 디비에 저장
			}
		}
		return result;
	}
	
	
// boardDelete
	@Override
	public int boardDelete(long num) throws Exception {
			List<BoardFileVO> list =boardFileDAO.fileList(num);
			//1.HDD에 해당 파일들을 삭제
			String path = servletContext.getRealPath("/resources/uploadnotice");
			System.out.println(path);
			
			for(BoardFileVO boardFileVO:list) {
				FileSaver fileSaver= new FileSaver();
				fileSaver.deleteFile(boardFileVO.getFileName(),path);
				}

			
			//2.DB에서 삭제
			int result=boardFileDAO.fileDeleteAll(num);
			if(result>0) {
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			
			
		return noticeDAO.boardDelete(num);
	}




}
