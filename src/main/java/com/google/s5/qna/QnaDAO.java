package com.google.s5.qna;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.s5.board.BoardDAO;
import com.google.s5.board.BoardVO;
@Repository
public class QnaDAO implements BoardDAO {

	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.google.s5.qna.QnaDAO.";
	//맨마지막에 .을 붙여줘야 나중에 NAMESPACE에서 .을 안써줄 수 있다.
	
	//COUNT
	@Override
	public long boardCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+"boardCount");
	}
		
	//LIST
	@Override
	public List<BoardVO> boardList(Map<String, Integer> map) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+"boardList",map);
	}
	//SELECT
	@Override
	public BoardVO boardSelect(long num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+"boardSelect", num);
	}
	//WRITE
	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(NAMESPACE+"boardWrite",boardVO);
	}
	//DELETE
	@Override
	public int boardDelete(long num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(NAMESPACE+"boardDelete",num);
	}
	//UPDATE
	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return  sqlSession.update(NAMESPACE+"boardUpdate",boardVO);
	}
	//HIT UPDATE
	@Override
	public int hitUpdate(long num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(NAMESPACE+"hitUpdate", num);
	}
	
}
