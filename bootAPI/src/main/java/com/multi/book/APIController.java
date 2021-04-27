package com.multi.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class APIController {
	
	@Autowired
	private BookService service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	//키워드 없어도 오류나지 않도록 requestd=false 설정
	@RequestMapping("/bookList")
	public ModelAndView bookList(@RequestParam(required=false) String keyword) {
		ModelAndView mv = new ModelAndView();
		
		if(keyword != null) {
			mv.addObject("bookList", service.searchBook(keyword, 10, 1));
		}
		
		mv.setViewName("bookListView");
		return mv;
	}
}
