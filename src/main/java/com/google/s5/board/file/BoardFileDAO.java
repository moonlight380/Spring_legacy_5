package com.google.s5.board.file;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.s5.board.BoardVO;
@Repository
public class BoardFileDAO {
	
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.google.s5.board.file.BoardFileDAO.";
	//맨마지막에 .을 붙여줘야 나중에 NAMESPACE에서 .을 안써줄 수 있다.

	//select
	public BoardFileVO fileSelect(BoardFileVO boardFileVO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"fileSelect",boardFileVO);
	}
	//INSERT -> return int 
	public int fileInsert(BoardFileVO boardFileVO) throws Exception {
		
		return sqlSession.insert(NAMESPACE+"fileInsert",boardFileVO);
	}
	
}
