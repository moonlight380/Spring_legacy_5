package com.google.s5.qna;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.s5.board.BoardService;
import com.google.s5.board.BoardVO;
@Service
public class QnaService implements BoardService {

	@Override
	public List<BoardVO> boardList(int curPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO boardSelect(long num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardupdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardDelete(long num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
