package com.google.s5.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.s5.board.page.Pager;

public interface BoardDAO {
	//멤버변수로 상수
	//추상메서드
	//public abstract //abstract
	//생략해도 자동으로 만들어짐
	
	//count
	public long boardCount(Pager pager) throws Exception;
	
	
	
	
	//List
	//public abstract
	//타입은 부모형태인 BoardVO를 넣는다. ArrayList도 부모형태인 List로 받아준다
	//public List<BoardVO> boardList(Map<String,Integer> map) throws Exception;
	public List<BoardVO> boardList(Pager pager) throws Exception;
		
	//select
	public BoardVO boardSelect(long num) throws Exception;
	
	//Insert


	public int boardWrite(BoardVO boardVO)throws Exception;
	
	//delete
	public int  boardDelete(long num) throws Exception;
	
	
	//update
	public int boardUpdate(BoardVO boardVO) throws Exception;
	
	//hit update
	public int hitUpdate(long num) throws Exception;

}
