package com.google.s5.Member.MemberFile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.s5.AbstractTestCase;
import com.google.s5.member.memberFile.MemberFileDAO;
import com.google.s5.member.memberFile.MemberFileVO;

public class MemberFileTest extends AbstractTestCase{

	@Autowired
	private MemberFileDAO memberFileDAO;
	
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test 
	public void memberFileTest( ) throws Exception{
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId("1");
		memberFileVO.setFileName("filename");
		memberFileVO.setOriName("oriName");
		int result = memberFileDAO.fileInsert(memberFileVO);
		assertEquals(1, result);
	}
	@Test
	public void memberUpdate() throws Exception{
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId("1");
		memberFileVO.setFileName("1");
		memberFileVO.setOriName("1");
		int result = memberFileDAO.fileInsert(memberFileVO);
		assertEquals(1, result);
	}
	
	
	
}
