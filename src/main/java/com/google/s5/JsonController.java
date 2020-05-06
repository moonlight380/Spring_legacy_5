package com.google.s5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.s5.board.BoardVO;
import com.google.s5.notice.NoticeService;
import com.google.s5.util.Pager;

@RestController
@RequestMapping("/json/**")
public class JsonController {
	@Autowired
	private NoticeService noticeService;
	
	
	@GetMapping("json1")
	@ResponseBody //return 하는 BoardVO를 VIEW로 보내지말고 클라이언트로 가라(호출한 쪽으로) ajax일 때만 쓸 수 있음
	//리턴하는 타입을 바꿔주면 알아서  json으로 바꿔줌
	public List<BoardVO> json1(Pager pager) throws Exception{
		
		BoardVO boardVO=noticeService.boardSelect(30);
		List<BoardVO> ar= noticeService.boardList(pager);
		
		
		//ModelAndView mv = new ModelAndView();
		//jackson dataBace
//		String json="{";
//		json=json+"\"num\":\""+boardVO.getNum()+"\",";
//		json=json+"\"title\":\""+boardVO.getTitle()+"\"}"; 
//		mv.addObject("result", "{\"name\":\"iu\"}"); 
//		//"name:iu" 라고 하면 따옴표가 없음 
//		mv.addObject("result",json);
//		mv.setViewName("common/ajaxResult");

		return ar;
		
	}
}
