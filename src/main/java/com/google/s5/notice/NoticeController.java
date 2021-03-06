package com.google.s5.notice;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.s5.board.BoardVO;
import com.google.s5.board.file.BoardFileVO;
import com.google.s5.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	
	@ModelAttribute("board")
	public String getBoard()throws Exception{
		return "notice";
	}

	//noticeList
	@RequestMapping(value ="noticeList", method = RequestMethod.GET )
	//밸류에는 요청 url/ 위에 노티스가 있기 때문에 그 아래로 적어주면 됨
	public ModelAndView boardList(Pager pager, ModelAndView mv) throws Exception{	
		
		System.out.println("kind:"+pager.getKind());
		System.out.println("search:"+pager.getSearch());
		//System.out.println(pager); //참조변수를 찍으면 객체의 주소값이 나옴
		
		//매개변수로 받아도 되고 , new해서 받아도 된다.
		List<BoardVO> ar=noticeService.boardList(pager);
		
		//어레이리스트의 부모 리스트
		System.out.println(pager.getTotalPage());
		
		mv.addObject("list",ar);
		mv.addObject("pager",pager);
		/* mv.addObject("board","notice"); 개발자 귀찮 위로 @ModelAttribute("board")이쪽*/
		mv.setViewName("board/boardList");
		return mv;	
	}
	
	
	//noticeSelect
	@RequestMapping(value ="noticeSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(long num,HttpSession session) throws Exception{
		BoardVO boardVO=noticeService.boardSelect(num);
		ModelAndView mv= new ModelAndView();
		mv.addObject("vo",boardVO);
		session.setAttribute("vo", boardVO);
		mv.setViewName("board/boardSelect");//서버내부에서 사용하는 주소 boardSelect로 보내려고 함
		return mv;
	}
	
	
	//noticeWrite
	@RequestMapping(value ="noticeWrite", method = RequestMethod.GET)
	public ModelAndView boardWrite(BoardVO boardVO,ModelAndView mv) throws Exception{
		
		mv.setViewName("board/boardWrite");
		return mv;
	}
	//noticeWrite_post
	@RequestMapping(value ="noticeWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(HttpServletRequest request,NoticeVO noticeVO, ModelAndView mv, MultipartFile[] files) throws Exception{
//		for(MultipartFile file:files) {
//			System.out.println(file.getOriginalFilename());
//		}
//		//파라미터 이름 알아보기<버그>
//		Enumeration<String> er=request.getParameterNames();
//		//몇개들어간지 알 수 없기 때문<버그>
//		while(er.hasMoreElements()) {
//			System.out.println(er.nextElement());
//		}
//		
		
		int result=noticeService.boardWrite(noticeVO,files);	
		//result=0;
		if(result>0) {
			mv.setViewName("redirect:./noticeList");
		}else {
			mv.addObject("result","WRITER FAIL");
			mv.addObject("path","./noticeList");
			mv.setViewName("common/result");
		}
		
		return mv;
	}
	
	
	//update
	@RequestMapping(value = "noticeUpdate", method=RequestMethod.GET)
	public ModelAndView boardUpdate(long num,ModelAndView mv, Model model) throws Exception {		
		BoardVO boardVO=noticeService.boardSelect(num);
		mv.addObject("vo", boardVO);
		mv.setViewName("board/boardUpdate");
		NoticeVO noticeVO = (NoticeVO)boardVO;
		model.addAttribute("size", noticeVO.getBoardFileVOs().size());
		return mv;
	}
	
//noticeUpdate",POST
	@RequestMapping(value = "noticeUpdate", method=RequestMethod.POST)
	public String boardUpdate(HttpServletRequest request,NoticeVO noticeVO,ModelAndView mv,BoardVO boardVO,MultipartFile[] files) throws Exception {
		for(MultipartFile multipartFile:files) {
			System.out.println("files:"+multipartFile.getOriginalFilename());
		}
		
		
		
//		//파라미터 이름 알아보기<버그>
//		Enumeration<String> er=request.getParameterNames();
//		//몇개들어간지 알 수 없기 때문<버그>
//		while(er.hasMoreElements()) {
//			System.out.println(er.nextElement());
//		}
//		
		int result=noticeService.boardupdate(noticeVO,files);
		//result=0;
		String path="";
		if(result>0) {
			path="redirect:./noticeList";
			
		}else {
			path="redirect:./noticeSelect?num="+noticeVO.getNum(); //select에서는 num을 주기로 했었음
		}
		
		return path;		
	}
	
	
	//delete
	@RequestMapping(value = "noticeDelete", method=RequestMethod.GET)
	public ModelAndView boardDelete(long num,NoticeVO noticeVO,ModelAndView mv) throws Exception {
		
		int result=noticeService.boardDelete(num);
		if(result>0) {
			mv.addObject("result","Delete Success");
			
			
		}else {
			mv.addObject("result","Delete Fail");
		}
		mv.addObject("path","./noticeList");
		mv.setViewName("common/result");
		return mv;
		
	}
	
	//fileList
		@RequestMapping(value = "fileList", method=RequestMethod.GET)
		public ModelAndView fileList(ModelAndView mv,BoardFileVO boardFileVO) throws Exception{
			
			
			mv.addObject("file",boardFileVO);
			return mv;
		}
	


	
}
