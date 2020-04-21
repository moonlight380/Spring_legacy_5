package com.google.s5.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.s5.board.BoardDAO;
import com.google.s5.board.BoardVO;
import com.google.s5.util.Pager;

@Repository
public class QnaDAO implements BoardDAO {

	
	@Autowired // 만들어 달라고 하는 객체를 주입시켜라
	private SqlSession sqlSession; //xml 파일에서 넣어둠 . 만들어 달라고 세팅
	private final String NAMESPACE="com.google.s5.qna.QnaDAO."; // 매퍼에 있는 nameSpace와 같은 걸 써두기 끝에 아이디명 연결해야 해서 . 넣기
	//맨마지막에 .을 붙여줘야 나중에 NAMESPACE에서 .을 안써줄 수 있다.
	
	//COUNT
	@Override
	public long boardCount(Pager pager) throws Exception {
		// id를 메서드 명과 동일하게 하면 좋음
		return sqlSession.selectOne(NAMESPACE+"boardCount",pager);
	}
		
	//LIST
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+"boardList",pager);
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
