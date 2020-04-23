package com.google.s5.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.s5.board.BoardDAO;
import com.google.s5.board.BoardVO;
import com.google.s5.util.Pager;

import java.util.Map;
@Repository
public class NoticeDAO implements BoardDAO{
	
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.google.s5.notice.NoticeDAO.";
	//맨마지막에 .을 붙여줘야 나중에 NAMESPACE에서 .을 안써줄 수 있다.
	
	//dual에서 먼저 조회
	public long boardNum() throws Exception{
		return sqlSession.selectOne(NAMESPACE+"boardNum");
		//파라미터 없어서 이름만 적어줌.nextval가 온다.
	}
	
	//LIST
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("noticeDAO IN");
		return sqlSession.selectList(NAMESPACE+"boardList",pager);
	}
	//SELECT
	@Override
	public BoardVO boardSelect(long num) throws Exception {
		// 여기에 있는 변수명이 파라미터 변수명과 같아야 한다
		return sqlSession.selectOne(NAMESPACE+"boardSelect", num);
		
	}

	@Override
	//상속받은 메서드의 선언부가 같아야 함 . 즉 선언부가 같아야 함. 
	public int boardWrite(BoardVO boardVO) throws Exception {
		//Connection con =null; SQL 세션 따라가면 있음
		//String sql="insert into notice values(board_seq.nextval, ?,?,?,sysdate,0)";
		//SQL 따라가면 있음
		
		return sqlSession.insert(NAMESPACE+"boardWrite",boardVO);
	}

	@Override
	public int boardDelete(long num) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"boardDelete",num);
	}

	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		
		return  sqlSession.update(NAMESPACE+"boardUpdate",boardVO);
	}

	@Override
	public int hitUpdate(long num) throws Exception {
		
		return sqlSession.update(NAMESPACE+"hitUpdate", num);
	}

	//count
	@Override
	public long boardCount(Pager pager) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+"boardCount", pager);
	}



}
