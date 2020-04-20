package com.google.s5.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.google.s5.board.BoardVO;
import com.google.s5.member.memberPage.MemberPage;

@RequestMapping("/member/**")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	//List
		@RequestMapping(value ="memberList", method = RequestMethod.GET )
		public ModelAndView memberList(MemberPage mp, ModelAndView mv,MemberVO memberVO) throws Exception{	

			List<MemberVO> ar=memberService.memberList(mp);

			
			mv.addObject("vo",memberVO);
			mv.addObject("mlist",ar);
			mv.addObject("mp",mp);
			mv.setViewName("member/memberList");
			return mv;	
		}
		
	//Join
		@RequestMapping(value = "memberJoin", method = RequestMethod.GET)
		public String memberJoin() throws Exception{
			
			return "member/memberJoin";

		}
		@RequestMapping(value = "memberJoin", method = RequestMethod.POST)
		public ModelAndView memberJoin(ModelAndView mv, MemberVO memberVO) throws Exception{
			
			int result = memberService.memberJoin(memberVO);
			if(result>0) {
				mv.setViewName("redirect:./memberList");
			}else {
				mv.addObject("result","WRITER FAIL");
				mv.addObject("path","./memberList");
				mv.setViewName("common/result");
			}
			return mv;
		}
	
	//LOGIN
		@RequestMapping(value = "memberLogin", method = RequestMethod.GET)
		public String memberLogin() throws Exception{
			
			return "member/memberLogin";
		}

		@RequestMapping(value = "memberLogin", method =RequestMethod.POST )
		public ModelAndView memberLogin(ModelAndView mv, MemberVO memberVO, HttpSession session) throws Exception{
			session.setAttribute("member", memberVO);
			
			memberVO= memberService.memberLogin(memberVO);
			
			if(memberVO!=null) {
				mv.setViewName("redirect:../");
			}else {
				mv.addObject("result","Login FAIL");
				mv.addObject("path","./memberJoin");
				mv.setViewName("common/result");
			}
			return mv;
		}

	//memberPage
		@RequestMapping(value = "memberPage", method = RequestMethod.GET)
		public String memberPage( MemberVO memberVO,HttpSession session,ModelAndView mv) throws Exception{
			session.setAttribute("page", memberVO);
			mv.addObject("member", memberVO);
			
			return "member/memberPage";
		}

	//memberUpdate
		@RequestMapping(value = "memberUpdate", method = RequestMethod.GET)
		public ModelAndView memberUpdate(MemberVO memberVO)throws Exception{
			ModelAndView mv = new ModelAndView();
			System.out.println("memberUpdate get");
			mv.addObject("member", memberVO);
			mv.setViewName("member/memberUpdate");
			return mv;
			
		}
		
		@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
		public String memberUpdate(MemberVO memberVO,ModelAndView mv)throws Exception{
			System.out.println("member controller post");
			int result=memberService.memberUpdate(memberVO);
			String path="";
			if(result>0) {
				path="redirect:./memberList";
				
			}else {
				path="redirect:./memberUpdate";
			}
			
			
			return path;
		}
		
		
		
		
		
		
		
}

