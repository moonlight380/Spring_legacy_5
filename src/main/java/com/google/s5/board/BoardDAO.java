package com.google.s5.board;

import java.util.ArrayList;
import java.util.List;

public interface BoardDAO {
	//멤버변수로 상수
	//추상메서드
	//public abstract //abstract
	//생략해도 자동으로 만들어짐
	
	
	//List
	//public abstract
	//타입은 부모형태인 BoardVO를 넣는다. ArrayList도 부모형태인 List로 받아준다
	public List<BoardVO> boardList() throws Exception;
		
		
	//select
	public BoardVO boardSelect() throws Exception;
	
	//Insert


	public int boardWrite(BoardVO boardVO)throws Exception;
	
	//delete
	public int  boardDelete(long num) throws Exception;
	
	
	//update
	public int boardUpdate(BoardVO boardVO) throws Exception;
	
	//hit update
	public int hitUpdate(BoardVO boardVO) throws Exception;

}
