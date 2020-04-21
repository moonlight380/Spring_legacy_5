package com.google.s5.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.google.s5.board.BoardVO;

import com.google.s5.util.Pager;


@Controller
@RequestMapping("/qna/**")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard()throws Exception{
		return "qna";
	}
	
	//qnaList
	@GetMapping("qnaList") //get 방식이면 get 매핑
	//@RequestMapping(value ="qnaList", method = RequestMethod.GET )
	//밸류에는 요청 url/ 
	public ModelAndView boardList(Pager pager, ModelAndView mv) throws Exception{
	
		List<BoardVO> ar=qnaService.boardList(pager);
		mv.addObject("list",ar);
		mv.addObject("pager",pager);
		mv.setViewName("board/boardList");
		return mv;	
	}
	
//qnaSelect
	@GetMapping("qnaSelect") 
	//@RequestMapping(value ="qnaSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(long num) throws Exception{
		BoardVO boardVO=qnaService.boardSelect(num);
		ModelAndView mv= new ModelAndView();
		mv.addObject("vo",boardVO);		
		mv.setViewName("board/boardSelect");//서버내부에서 사용하는 주소 boardSelect로 보내려고 함
		return mv;
	}
//qnaWrite
		@RequestMapping(value ="qnaWrite", method = RequestMethod.GET)
		public ModelAndView boardWrite(BoardVO boardVO,ModelAndView mv) throws Exception{
			
			mv.setViewName("board/boardWrite");
			return mv;
		}
		@PostMapping("qnaWrite")
		public ModelAndView boardWrite(QnaVO qnaVO ,ModelAndView mv) throws Exception{
			int result=qnaService.boardWrite(qnaVO);	
			String msg="QnA Write FAIL";
			//result=0;
			
			if(result>0) {
				msg = "Qna Write Success";
			}
			
			mv.addObject("result", msg);
			mv.addObject("path", "./qnaList");
			
			mv.setViewName("common/result");//web-inf 와 view 빼고
			return mv;
		}
			
		
			
//qnaupdate
		@RequestMapping(value = "qnaUpdate", method=RequestMethod.GET)
		public ModelAndView boardUpdate(long num,Model model,ModelAndView mv) throws Exception {		
			BoardVO boardVO= qnaService.boardSelect(num);
			model.addAttribute("vo", boardVO);
			mv.setViewName("board/boardUpdate");
			return mv;
		}

		@RequestMapping(value = "qnaUpdate", method=RequestMethod.POST)
		public String boardUpdate(QnaVO qnaVO,ModelAndView mv) throws Exception {
			int result=qnaService.boardupdate(qnaVO);
			//result=0;
			String path="";
			if(result>0) {
				path="redirect:./qnaList";
				
			}else {
				path="redirect:./qnaSelect?num="+qnaVO.getNum(); //select에서는 num을 주기로 했었음
			}
			
			return path;		
		}
		
//delete
		@RequestMapping(value = "qnaDelete", method=RequestMethod.GET)
		public ModelAndView boardDelete(long num,QnaVO qnaVO,ModelAndView mv) throws Exception {
			
			int result=qnaService.boardDelete(num);
			if(result>0) {
				mv.addObject("result","QNA Delete Success");
				
				
			}else {
				mv.addObject("result","QNA Delete Fail");
			}
			mv.addObject("path","./qnaList");
			mv.setViewName("common/result");
			return mv;
			
		}
//reply		
		@GetMapping("qnaReply")
		public ModelAndView boardReply(ModelAndView mv,long num) throws Exception{
			mv.addObject("num", num);   //부모의 글번호
			mv.setViewName("board/boardReply");
			return mv;
		}
		@PostMapping("qnaReply")
		public ModelAndView boardReply(ModelAndView mv,QnaVO qnaVO) throws Exception{
		
			mv.setViewName("board/boardReply");
			return mv;
		}
		
		
		
		
		
}
//END CLASS
