package com.google.s5.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.s5.board.BoardService;
import com.google.s5.board.BoardVO;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO; //서비스는 디에오에 대해 의존적	
	//List
	@Override
	public List<BoardVO> boardList(int curPage) throws Exception {
		System.out.println("service");
		int startRow =(curPage-1)*10+1;
		int lastRow= curPage*10;
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("lastRow", lastRow);
		//--------------------------------------------
		//1.총 글의 개수
		long totalCount= noticeDAO.boardCount();
		System.out.println("totalcount:"+totalCount);
		
		//2.총 페이지의 개수
		long totalPage= totalCount/10;
		if(totalCount%10!=0) {
			totalPage++;
		}
		System.out.println("totalpage:"+totalPage);
		return noticeDAO.boardList(map);
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
