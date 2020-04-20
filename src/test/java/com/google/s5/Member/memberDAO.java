package com.google.s5.Member;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.google.s5.member.MemberDAO;
import com.google.s5.member.MemberVO;
import com.google.s5.member.memberPage.MemberPage;

public class memberDAO {
@Autowired
private MemberDAO memberDAO;
	
		public List<MemberVO> memberList(MemberPage mp) throws Exception{
		
			return memberDAO.memberList(mp);
		}
		
		//List 메서드 불러오기
		@Test
		public void daoTest(MemberPage mp) throws Exception{
			List<MemberVO> ar =this.memberList(mp);
			assertNotEquals(0, ar.size());
		}
	

}
