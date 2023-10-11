package com.spring.myweb.freeboard.service;

import java.util.List;

import com.spring.myweb.freeboard.dto.FreeDetailDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;

public interface IFreeBoardService {

	// 글 등록
	void regist(FreeRegistRequestDTO dto);
	
	// 글 목록
	List<FreeListResponseDTO> getList();
	
	// 상세 보기
	FreeDetailDTO getContent(int bno);
	
	// 수정
	void update(FreeBoard freeboard);
	
	// 삭제
	void delete(int bno);
	
}
