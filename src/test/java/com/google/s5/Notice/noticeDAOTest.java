package com.google.s5.Notice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.s5.AbstractTestCase;
import com.google.s5.board.BoardVO;
import com.google.s5.notice.NoticeDAO;
import com.google.s5.notice.NoticeVO;

public class noticeDAOTest extends AbstractTestCase{
		@Autowired
		private NoticeDAO noticeDAO;
		@Test
		public void daoIsNull() {
			assertNotNull(noticeDAO);
		}
		//라이트
		/*
		 * @Test public void boardWriteTest() throws Exception{ NoticeVO noticeVO = new
		 * NoticeVO(); noticeVO.setTitle("test title");
		 * noticeVO.setWriter("test Writer"); 
		 * noticeVO.setContents("test contents");
		 * int result= noticeDAO.boardWrite(noticeVO);
		 * 
		 * assertEquals(1, result); }
		 */
		/*
		 * //삭제
		 * 
		 * @Test public void boardDelete() throws Exception{
		 * 
		 * int result= noticeDAO.boardDelete(11); assertNotEquals(0, result); }
		 */
		 
		
		/*
		 * //보드 업데이트
		 * 
		 * @Test 
		 * public void boardUpdate() throws Exception{ 
		 * NoticeVO noticeVO = new NoticeVO(); 
		 * noticeVO.setContents("dd"); noticeVO.setTitle("dd");
		 * noticeVO.setNum(13); int result= noticeDAO.boardUpdate(noticeVO);
		 * assertEquals(1, result); }
		 */
		
		 //히트업데이트
		@Test
		public void hitUpdate() throws Exception{
		int result= noticeDAO.hitUpdate(10);
		assertEquals(1, result); 
		}
		

}
