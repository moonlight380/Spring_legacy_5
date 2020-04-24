package com.google.s5.memo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.s5.util.Pager;

@Controller
@RequestMapping("/memo/**")
public class MemoController {
	@Autowired
	private MemoService memoService;
	
	@GetMapping("getList")
	public void getList(Pager pager,Model model) throws Exception {
		List<MemoVO> ar=memoService.memoList(pager);
		model.addAttribute("list", ar);
		}
	
	@GetMapping("memoList")
	public void memoList(Pager pager) throws Exception {
		memoService.memoList(pager);	
	}
	@PostMapping("memoInsert")
	public ModelAndView memoInsert(MemoVO memoVO,ModelAndView mv ) throws Exception{
		
		int result =memoService.memoInsert(memoVO);
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		//ajaxResult로 가서 html를 받아서 오자
		//경로 이미 xml에서 정해줌
		return mv;
	}
	
	
}
