package com.google.s5.board.file;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.s5.AbstractTestCase;
import com.google.s5.board.file.BoardFileDAO;
import com.google.s5.board.file.BoardFileVO;


public class BoardFileTest  extends AbstractTestCase {

	@Autowired
	private BoardFileDAO boardFileDAO; 
	
	@Test
	public void fileInsertTest() throws Exception {
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setBoard(1);
		boardFileVO.setFileName("nana");
		boardFileVO.setOriName("nanaOriname");
		boardFileVO.setFileNum(85);
		int result=boardFileDAO.fileInsert(boardFileVO);
		assertEquals(1, result);
	}
}
