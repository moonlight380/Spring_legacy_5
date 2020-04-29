package com.google.s5.board.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/boardFile/**")
public class BoardFileController {

	
	@Autowired
	private BoardFileService boardFileService;
	
	@PostMapping("summerDelete")
	public ModelAndView fileDelete(String fileName)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardFileService.fileDelete(fileName); //호출
		mv.addObject("result",result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	
	@PostMapping("fileInsert")
	public ModelAndView fileInsert (MultipartFile files,ModelAndView mv) throws Exception{
		String fileName = boardFileService.fileInsert(files);
		mv.addObject("result",fileName);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	
	
	//reponse에 들어가는 바디에 들어간다. 
	@PostMapping("fileDelete")
	@ResponseBody//꼭 키가 없어도 데이터만 보내도 데이터만 나온다. 
	public int fileDelete(BoardFileVO boardFileVO)throws Exception{
		//ModelAndView mv = new ModelAndView();	
		int result = boardFileService.fileDelete(boardFileVO);
		
//		mv.addObject("result", result);
//		mv.setViewName("common/ajaxResult");
		
		return result;		
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
