package com.google.s5.Qna;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.s5.notice.NoticeDAO;
import com.google.s5.qna.QnaDAO;

public class qnaDAOtest {
	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Test
	public void daoIsNull() {
		assertNotNull(qnaDAO);
	}
	
}
