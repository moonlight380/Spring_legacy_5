package com.google.s5.board;

import java.util.List;

import com.google.s5.util.Pager;

public interface BoardService {
	//List
		public List<BoardVO> boardList(Pager pager) throws Exception;
	//Select
		public BoardVO boardSelect(long num) throws Exception;
	//Insert
		public int boardWrite(BoardVO boardVO) throws Exception;
	//Update
		public int boardupdate(BoardVO boardVO) throws Exception;
	//Delete
		public int boardDelete(long num) throws Exception;
	

}
