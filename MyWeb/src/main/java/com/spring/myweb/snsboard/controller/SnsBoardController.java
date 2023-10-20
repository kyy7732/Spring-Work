package com.spring.myweb.snsboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.snsboard.service.SnsBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/snsboard")
@RequiredArgsConstructor
public class SnsBoardController {
	
	private final SnsBoardService service;
	
	@GetMapping("/snsList")
	public ModelAndView snsList() {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("name", "value"); // 값 넣는 법
		mv.setViewName("snsboard/snsList");
		return mv; // @restController에서 modelAndview로 리턴하면 Controller처럼 이용 가능하다 
		
	} 
	
	
}






























