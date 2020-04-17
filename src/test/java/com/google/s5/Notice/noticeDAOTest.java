package com.google.s5.Notice;

import static org.junit.Assert.*;

import java.util.List;

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
		
		@Test
		public void boardWriteTest()throws Exception {
			String writer="";
			String title="";
			String contents="";
			for(int i=0;i<150;i++) {
				NoticeVO noticeVO = new NoticeVO();
				if(i%3==0) {
					writer="iu";
					title="Alert";
					contents="Samsung";
				}else if(i%3==1) {
					writer="choa";
					title="computer";
					contents="apple";
				}else {
					writer="suji";
					title="os";
					contents="linux";
				}
				noticeVO.setTitle(title+i);
				noticeVO.setWriter(writer);
				noticeVO.setContents(contents+i);
				int result = noticeDAO.boardWrite(noticeVO);
				if(i==50 || i==100) {
					Thread.sleep(1000);
				}
			}
		}
		
		 //삭제
		 //@Test
		public void boardDeleteTest()throws Exception{
			int result = noticeDAO.boardDelete(1);
			assertNotEquals(0, result);
		}
		  //보드 업데이트
		  
		 //@Test 
		 public void boardUpdate() throws Exception{ 
		 NoticeVO noticeVO = new NoticeVO(); 
		 noticeVO.setContents("dd"); noticeVO.setTitle("dd");
		 noticeVO.setNum(13); int result= noticeDAO.boardUpdate(noticeVO);
		  assertEquals(1, result); }
		 
		
		 //히트업데이트
		//@Test
		public void hitUpdate() throws Exception{
		int result= noticeDAO.hitUpdate(10);
		assertEquals(1, result); 
		}
		
		//select
		//@Test
		public BoardVO boardSelectTest() throws Exception{
			BoardVO boardVO = noticeDAO.boardSelect(10);
			return boardVO;
			
		}
		//List
		public List<BoardVO> boardList() throws Exception{
			return noticeDAO.boardList(null);
		}
		
		//List 메서드 불러오기
		@Test
		public void daoTest() throws Exception{
			List<BoardVO> ar =this.boardList();
			assertNotEquals(0, ar.size());
		}
		
		//샐렉드 메서드 불러오기
		@Test
		public void dotest() throws Exception{
			BoardVO boardVO =this.boardSelectTest();
			assertNotNull(boardVO);
		}
		
	//만들때는 디에이오 담을 때는 보드 브이오
		

}
