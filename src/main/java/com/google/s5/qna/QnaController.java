package com.google.s5.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.s5.board.BoardVO;
import com.google.s5.notice.NoticeVO;


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
	@RequestMapping(value ="qnaList", method = RequestMethod.GET )
	//밸류에는 요청 url/ 
	public ModelAndView boardList(@RequestParam(defaultValue = "1")int curPage, ModelAndView mv) throws Exception{
	
		List<BoardVO> ar=qnaService.boardList(curPage);
		System.out.println("qnaCurPage:"+curPage);
		
		mv.addObject("list",ar);		
		mv.setViewName("board/boardList");
		return mv;	
	}
	
	//qnaSelect
	@RequestMapping(value ="qnaSelect", method = RequestMethod.GET)
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
		@RequestMapping(value ="qnaWrite", method = RequestMethod.POST)
		public ModelAndView boardWrite(QnaVO qnaVO ,ModelAndView mv) throws Exception{
			int result=qnaService.boardWrite(qnaVO);	
			//result=0;
			if(result>0) {
				mv.setViewName("redirect:./qnaList");
			}else {
				mv.addObject("result","QNA WRITER FAIL");
				mv.addObject("path","./qnaList");
				mv.setViewName("common/result");
			}
			
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
}
//END CLASS
