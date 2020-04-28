package com.google.s5.qna;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.s5.board.BoardService;
import com.google.s5.board.BoardVO;
import com.google.s5.board.file.BoardFileDAO;
import com.google.s5.board.file.BoardFileVO;
import com.google.s5.notice.NoticeDAO;
import com.google.s5.util.FileSaver;
import com.google.s5.util.Pager;
@Service
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO; //서비스는 디에오에 대해 의존적	
	@Autowired 
	private FileSaver fileSaver;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private BoardFileDAO boardFileDAO;
	
	// reply
	public int boardReply(BoardVO boardVO) throws Exception{
		int result=qnaDAO.boardReplyUpdate(boardVO);
		result=qnaDAO.boardReply(boardVO);
		return result;
	}
	
	//list
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePage(qnaDAO.boardCount(pager));

		//--------------------------------------------
		//1.총 글의 개수
		long totalCount= qnaDAO.boardCount(pager);
		System.out.println("totalcount:"+totalCount);
		
		//2.총 페이지의 개수
		long totalPage= totalCount/10;
		if(totalCount%10!=0) {
			totalPage++;
		}
		System.out.println("totalpage:"+totalPage);
		return qnaDAO.boardList(pager);
	}
	//select
	@Override
	public BoardVO boardSelect(long num) throws Exception {
		qnaDAO.boardSelect(num);
		return qnaDAO.boardSelect(num);
	}
	//write
	@Override
	public int boardWrite(BoardVO boardVO,MultipartFile[] files) throws Exception {
		
		//1.sequnce num qna table insert
		int result =qnaDAO.boardWrite(boardVO);
		//2.qna table insert
		//mapper에서 함
		//3.Hdd에 파일 저장하고 boardFile table insert
		String path = servletContext.getRealPath("/resources/uploadqna");
	
		for(MultipartFile file:files) {
			BoardFileVO boardFileVO = new BoardFileVO(); // 한번 돌때마다 새로운 파일
			String fileName=fileSaver.saveByTransfer(file, path);
			boardFileVO.setNum(boardVO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(2);
			boardFileDAO.fileInsert(boardFileVO); //파일의 갯수만큼이라서 반복문 안에
		}
		
		return result;//qnaDAO.boardWrite(boardVO);

	}
	
	
	//update
	
	public int boardupdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.boardUpdate(boardVO);
	}
	//delete
	@Override
	public int boardDelete(long num) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.boardDelete(num);
	}

	@Override
	public int boardupdate(BoardVO boardVO, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
