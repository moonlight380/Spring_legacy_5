package com.google.s5.member;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.google.s5.board.BoardVO;
import com.google.s5.member.memberFile.MemberFileVO;
import com.google.s5.member.memberPage.MemberPage;
import com.google.s5.util.Pager;

@RequestMapping("/member/**")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	//memberIdCheck
	
	
	  //void면 url 경로가 뷰의 이름이 된다. 
	 
	@PostMapping("memberIdCheck")
	public ModelAndView memberIdCheck(ModelAndView mv,MemberVO memberVO) throws Exception{	
		memberVO=memberService.memberIdCheck(memberVO);
		//null -> 가입이 가능한 아이디 //1
		//null 이 아니면  -> 중복//0
		//1과 0을 어딘가에서 출력을 받아주고 그걸 다시 받으면 된다.
		
		int result=0;
		if(memberVO== null) {	
			result=1;
		}
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		
		return mv;
	}
	
	
	@GetMapping(value = "memberCheck")
	public ModelAndView memberCheck(ModelAndView mv,MemberVO memberVO)throws Exception{
		mv.setViewName("member/memberList");
		return mv;	
	}
	//memberLists
	@GetMapping("memberLists")
	public ModelAndView memberLists(Pager pager, ModelAndView mv )throws Exception{
		List<MemberVO> ar = memberService.memberList(pager);
		mv.addObject("mlist",ar);
		mv.addObject("pager",pager);
		mv.setViewName("member/memberLists");
		return mv;
	}
	//List
		@RequestMapping(value ="memberList", method = RequestMethod.GET )
		public ModelAndView memberList(Pager mp, ModelAndView mv,MemberVO memberVO) throws Exception{	

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
		public ModelAndView memberJoin(ModelAndView mv, MemberVO memberVO,	MultipartFile avatar,HttpSession session) throws Exception{
			
//			System.out.println("파일업로드 시 실제 이름:"+avatar.getOriginalFilename());
//			System.out.println("파라미터 이름:" +avatar.getName());
//			System.out.println("파일의 크기, 단위는 byte:"+avatar.getSize()); //설정한 파일 크기 범위 내에 들어와야 한다.
//			System.out.println("파일 형식"+avatar.getContentType());
//			//avatar.getBytes();//
//					
			
			// 변수명을 동일하게 <MultipartFilter avatar>
			int result = memberService.memberJoin(memberVO, avatar, session);
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
		public String memberLogin(@CookieValue(value="cId",required = false) String cId, Model model) throws Exception{
			//꼭 값이 안와도 된다.required = false
			System.out.println(cId);
			//model.addAttribute("cId",cId);
			return "member/memberLogin";
		}

		@RequestMapping(value = "memberLogin", method =RequestMethod.POST )
		public ModelAndView memberLogin(String remember,ModelAndView mv, MemberVO memberVO, HttpSession session,HttpServletResponse response) throws Exception{
			Cookie cookie =  new Cookie("cId", "");//디폴트 생성자 없음
			
		if(remember != null) {
			/* cookie=new Cookie("cId", memberVO.getId()); */
			cookie.setValue(memberVO.getId());
		}
		
		/* cookie.setMaxAge(0); */
		/* cookie.setValue(newValue); *///똑같은 쿠키 이름에 값을 다르게 줌
		response.addCookie(cookie);
		
			memberVO= memberService.memberLogin(memberVO);
			
			if(memberVO!=null) {
				mv.setViewName("redirect:../");
				session.setAttribute("member", memberVO); // 순서 주의 하기  로그인 후에 정보를 다 받을 수 있도록
				
			}else {
				mv.addObject("result","Login FAIL");
				mv.addObject("path","./memberJoin");
				mv.setViewName("common/result");
			}
			return mv;
		}
		
		@RequestMapping(value = "memberLogout")
		public String memberLogout(HttpSession session)throws Exception{
			session.invalidate();
			return "redirect:../";
		}
		
	//memberPage
		@RequestMapping(value= "memberPage")
		public void memberPage(HttpSession session, Model model ) throws Exception {
			//꺼내오면 OBJECT 라서 형변환
			//이미로그인 할 때 session에 담아서 하기 때문에 지워도 된다.
//			MemberVO memberVO=(MemberVO)session.getAttribute("member");
//			MemberFileVO memberFileVO=memberService.fileSelect(memberVO.getId());
//			model.addAttribute("file",memberFileVO);
			
		}
	//meberfileDELETE
		@GetMapping("fileDelete")
		public String fileDelete(HttpSession session, Model model ) throws Exception {
			MemberVO memberVO=(MemberVO)session.getAttribute("member");
			int result=memberService.fileDelete(memberVO.getId(),session);
			
			return "redirect:./memberPage";
		}
	//memberUpdate	
		@RequestMapping(value= "memberUpdate")
		public void memberUpdate() {
			
		}

	
		@RequestMapping(value= "memberUpdate", method = RequestMethod.POST)
		public ModelAndView memberUpdate(ModelAndView mv, MemberVO memberVO, HttpSession session) throws Exception {
			String id = ((MemberVO)session.getAttribute("member")).getId();
			memberVO.setId(id);
			
			int result = memberService.memberUpdate(memberVO);
			
			if(result>0) {
				session.setAttribute("member", memberVO);
				mv.setViewName("redirect:./memberPage");
			}else {
				 mv.addObject("result", "Update Fail");
				 mv.addObject("path", "./memberPage");
				 mv.setViewName("common/result");
			}
			
			return mv;
		}
//memberDelete		
		@RequestMapping(value= "memberDelete")
		public ModelAndView memberDelete(ModelAndView mv, HttpSession session) throws Exception {
			MemberVO memberVO = (MemberVO)session.getAttribute("member");
			int result = memberService.memberDelete(memberVO);
			if(result>0) {
			session.invalidate();
				mv.addObject("result", "Delete Success");
				mv.addObject("path", "../");
				mv.setViewName("common/result");
			}else {
				mv.addObject("result", "Delete Fail");
				mv.addObject("path", "../");
				mv.setViewName("common/result");
			}
			
			return mv;
		}
		
//@GetMapping(value="memberDeletes")
//﻿멤버 컨트롤러에 주소가 멤버딜리츠가 넘어오는 것을 받아서 넘어온 파라미터가 뭐뭐뭐 넘어왔는지 콘솔창에 찍어보기
		@GetMapping("memberDeletes")
		public ModelAndView memberDeletes(ModelAndView mv,String [] ids)throws Exception{
			//배열을 list로 변환
			List<String> list = Arrays.asList(ids);
			int result = memberService.memberDeletes(list);
			System.out.println(result);
			mv.addObject("result",result);
			mv.setViewName("common/ajaxResult");
			/*
			 * for(String id: ids){ MemberVO memberVO = new MemberVO(); memberVO.setId(id);
			 * memberService.memberDelete(memberVO); //System.out.println(id);//찍히지 않음.
			 * 파라미터는 보냈지만 ->jquery ajax 배열 전송->traditional:true }
			 */
			
			return mv;

		}
		

		
}

