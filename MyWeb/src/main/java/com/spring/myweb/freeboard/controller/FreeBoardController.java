package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.FreeDetailDTO;
import com.spring.myweb.freeboard.dto.FreeModifyRequestDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {
	
	private final IFreeBoardService service;
	
	// 목록 화면
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freeboard/freeList: GET!");
		
		model.addAttribute("boardList", service.getList());
		
	}
	
	// 글쓰기 페이지를 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist(){} 
	
	// 글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);
		return "redirect:/freeboard/freeList";
	}
	
	
	// 글 상세보기
	@GetMapping("/freeContent")
	public String content(int bno, Model model) {
		model.addAttribute("boardContent", service.getContent(bno));
		return "freeboard/freeDetail";
	}
	
	// 글 수정 페이지 이동 요청
	@PostMapping("/modPage") 
	// model에다 "article"이라는 이름으로 바로 보냄
	public String modPage(@ModelAttribute("article") FreeModifyRequestDTO dto) {
		return "freeboard/freeModify";
	}
	
	
	// 글 수정하기
	@PostMapping("/freeModify")
	public String modify(FreeModifyRequestDTO dto) {
		service.update(dto);
		return "redirect:/freeboard/freeContent?bno=" + dto.getBno();
	}
	
	// 글 삭제하기
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}
	
}


















