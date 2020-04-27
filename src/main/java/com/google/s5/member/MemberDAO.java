package com.google.s5.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.s5.board.BoardVO;
import com.google.s5.member.memberPage.MemberPage;
import com.google.s5.util.Pager;



@Repository
public class MemberDAO {
@Autowired
private  SqlSession sqlSession;
private final String NAMESPACE="com.google.s5.member.MemberDAO.";

//LIST
public List<MemberVO> memberList(Pager mp) throws Exception {
	// TODO Auto-generated method stub
	
	return sqlSession.selectList(NAMESPACE+"memberList",mp);
}
//count
public long memberCount(Pager mp) throws Exception {
	
	return sqlSession.selectOne(NAMESPACE+"memberCount", mp);

}

//MemberJoin
public int memberJoin(MemberVO memberVO) throws Exception{
	
	
	return sqlSession.insert(NAMESPACE+"memberJoin", memberVO);
	
}

//MemberLogin
public MemberVO memberLogin(MemberVO memberVO)throws Exception{
	
	return sqlSession.selectOne(NAMESPACE+"memberLogin", memberVO);
}
//MemberMypage
public MemberVO memberPage(MemberVO memberVO)throws Exception{
	
	return sqlSession.selectOne(NAMESPACE+"memberPage", memberVO);
}

//MemberUpdate
public int memberUpdate(MemberVO memberVO)throws Exception{
	return sqlSession.update(NAMESPACE+"memberUpdate", memberVO);
}

public int memberDelete(MemberVO memberVO)throws Exception{
	return sqlSession.delete(NAMESPACE+"memberDelete", memberVO);
}

//memberIdCheck
public MemberVO memberIdCheck(MemberVO memberVO)throws Exception{
	return sqlSession.selectOne(NAMESPACE+"memberIdCheck", memberVO);
}

//memberDeletes
public int memberDeletes (List<String>list) throws Exception{
	return sqlSession.delete(NAMESPACE+"memberDeletes",list);
}

}
