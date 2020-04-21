package com.google.s5.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.google.s5.member.memberPage.MemberPage;
import com.google.s5.util.Pager;




@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	//LIST
	public List<MemberVO> memberList(Pager mp) throws Exception {
		
		
		mp.makeRow();
		
		//--------------------------------------------
		long totalCount= memberDAO.memberCount(mp);
		mp.makePage(totalCount);
		//리턴을 안해도 totalCount가 controller에 가는데 왜 그럴까?
		//참조변수는 주소를 남기는데 이미 주소 안에  totalCount가 있기 때문
		return memberDAO.memberList(mp);
	}

	//MEMBER JOIN
	public int memberJoin(MemberVO memberVO) throws Exception{
		
		
		return memberDAO.memberJoin(memberVO);
	}


	//MEMBER LOGIN
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		
		return memberDAO.memberLogin(memberVO);
	}
	
	//MEMBER Page
		public MemberVO memberPage(MemberVO memberVO) throws Exception{
			System.out.println("memberservice IN");
			
			return memberDAO.memberPage(memberVO);
		}
		
		
		
	//Member Update
		public int memberUpdate(MemberVO memberVO) throws Exception{
			
			return memberDAO.memberUpdate(memberVO);
		}
}
