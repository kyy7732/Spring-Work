package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.page.PageCreator;
import com.spring.myweb.freeboard.dto.request.FreeModifyRequestDTO;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.response.FreeDetailDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {
	
	private final IFreeBoardService service;
	
	// 페이징이 들어간 목록 화면 // 페이징 : (페이지를 나눌 화면)
	@GetMapping("/freeList")
	public void freeList(Page page, Model model) {
		System.out.println("/freeboard/freeList: GET!");
		
		// 검색 시 데이터가 없을 때
		PageCreator creator; 
		int totalCount = service.getTotal(page); // 조회될 게시물에 개수를 구해옴
		if(totalCount == 0) { // 에초에 검색을 안했을때로 
			page.setKeyword(null); 
			page.setCondition(null);
			creator = new PageCreator(page, service.getTotal(page));
			model.addAttribute("msg", "searchFail");
		} else {
			creator = new PageCreator(page, totalCount);
		}
		
		
		model.addAttribute("boardList", service.getList(page));
		model.addAttribute("pc", creator);
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
	public String content(int bno, 
						Model model, 
						@ModelAttribute("p") Page page) {
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


















