package com.google.s5.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.s5.board.BoardService;
import com.google.s5.board.BoardVO;
import com.google.s5.notice.NoticeDAO;
@Service
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO; //서비스는 디에오에 대해 의존적	
	//list
	@Override
	public List<BoardVO> boardList(int curPage) throws Exception {
		System.out.println("QNA service");
		int startRow =(curPage-1)*10+1;
		int lastRow= curPage*10;
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("lastRow", lastRow);
		//--------------------------------------------
		//1.총 글의 개수
		long totalCount= qnaDAO.boardCount();
		System.out.println("totalcount:"+totalCount);
		
		//2.총 페이지의 개수
		long totalPage= totalCount/10;
		if(totalCount%10!=0) {
			totalPage++;
		}
		System.out.println("totalpage:"+totalPage);
		return qnaDAO.boardList(map);
	}
	//select
	@Override
	public BoardVO boardSelect(long num) throws Exception {
		qnaDAO.boardSelect(num);
		return qnaDAO.boardSelect(num);
	}
	//write
	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.boardWrite(boardVO);
	}
	//update
	@Override
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

}
