package com.google.s5.board.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/boardFile/**")
public class BoardFileController {

	
	@Autowired
	private BoardFileService boardFileService;
	//두개 다 넘기기 위해 매개변수 boardFileVO
	@PostMapping("fileDelete")
	public ModelAndView fileDelete(BoardFileVO boardFileVO)throws Exception{
		ModelAndView mv = new ModelAndView();	
		int result = boardFileService.fileDelete(boardFileVO);
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;		
	}
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(BoardFileVO boardFileVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardFileVO =boardFileService.fileSelect(boardFileVO);
		mv.addObject("file",boardFileVO);
		mv.setViewName("fileDown");
		return mv;
	}
	
	
	//fileList
		@RequestMapping(value = "fileList", method=RequestMethod.GET)
		public ModelAndView fileList(ModelAndView mv,BoardFileVO boardFileVO) throws Exception{
			mv.addObject("file",boardFileVO);
			return mv;
		}
	
}
