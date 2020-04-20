package com.google.s5.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.s5.board.BoardService;
import com.google.s5.board.BoardVO;
import com.google.s5.board.page.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO; //서비스는 디에오에 대해 의존적	
	//List
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		System.out.println("notice service in");
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

	@Override
	public BoardVO boardSelect(long num) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.boardSelect(num);
		return noticeDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.boardWrite(boardVO);
	}

	@Override
	public int boardupdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(long num) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.boardDelete(num);
	}




}
