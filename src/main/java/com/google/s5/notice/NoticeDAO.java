package com.google.s5.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.s5.board.BoardDAO;
import com.google.s5.board.BoardVO;

@Repository
public class NoticeDAO implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.google.s5.notice.NoticeDAO.";
	
	@Override
	public List<BoardVO> boardList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO boardSelect() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public int hitUpdate(BoardVO boardVO) throws Exception {
		
		return sqlSession.update(NAMESPACE+"hitUpdate", boardVO);
	}


}
