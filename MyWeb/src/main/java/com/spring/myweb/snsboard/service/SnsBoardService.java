package com.spring.myweb.snsboard.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.snsboard.controller.SnsBoardController;
import com.spring.myweb.snsboard.dto.SnsBoardRequestDTO;
import com.spring.myweb.snsboard.dto.SnsBoardResponseDTO;
import com.spring.myweb.snsboard.entity.SnsBoard;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SnsBoardService {

	private final ISnsBoardMapper mapper;

	public void insert(SnsBoardRequestDTO dto) {
		
		// 날짜별로 폴더를 생성해서 관리할 예정입니다. (yyyyMMdd)
		// 날짜는 LocalDateTime과 DateTimeFormatter를 이용하세요.
		
//		DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyyMMdd");
//		LocalDateTime locadate = LocalDateTime;
//	 	String fileLoca = now.format(datetime);
		
		// 기본 경로는 C:/test/upload로 사용 하겠습니다.
		 String uploadPath = "C:/test/upload/";
		 
		// 폴더가 존재하지 않다면 새롭게 생성해 주시라~
		 File folder = new File(uploadPath, getFolder());
		 	
		 
		 if(!folder.exists()) {
			 folder.mkdirs();
			 System.out.println("폴더 생성 완료!");
		 }
		// 저장될 파일명을 UUID를 이용한 파일명으로 저장합니다.
		// UUID가 제공하는 랜덤 문자열에 -을 제거해서 전부 사용하시면 됩니다.
		String fileRealName = dto.getFile().getOriginalFilename(); // 파일 원본명.
		 System.out.println("파일명: " + fileRealName);
		 
		 UUID uuid = UUID.randomUUID();
		 System.out.println("uuid: " + uuid.toString());
		 
		 // 확장자 추출.
		 String fileExtension = fileRealName.substring(fileRealName.lastIndexOf(".")); // .에서 부터 끝까지 추출
		 
		 String fileName = uuid.toString().replace("-", "") + fileExtension ;
		 System.out.println("fileName: " + fileName);
		 
		// 실제 전달된 파일을 지정한 로컬 경로에 전송(transferTo) 하세요.
		File saveFile = new File(uploadPath , getFolder() + "/" + fileName);
		
		try {
			dto.getFile().transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		// DB에 각각의 값을 저장하세요. (INSERT)
		// uploadPath -> "C:/test/upload"
		// fileLoca -> 날짜로 된 폴더명
		// fileName -> 랜덤 파일명
		// fileRealName -> 실제 파일명
		mapper.insert(SnsBoard.builder()
								.writer(dto.getWriter())
								.uploadPath(uploadPath)
								.fileLoca(getFolder())
								.fileName(fileName)
								.fileRealName(fileRealName)
								.content(dto.getContent())
								.build());
		
	}
	
	 private String getFolder() {
		  DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyyMMdd");
		  LocalDateTime locadate = LocalDateTime.now();
		  return locadate.format(datetime);
		}

	public List<SnsBoardResponseDTO> getList(int page) {
		List<SnsBoardResponseDTO> dtoList = new ArrayList<>();
		List<SnsBoard> list = mapper.getList(Page.builder()
							.pageNo(page)
							.amount(3)
							.build());
		for(SnsBoard board : list) {
			dtoList.add(new SnsBoardResponseDTO(board));
		}
		
		return dtoList;
	}

	public SnsBoardResponseDTO content(int bno) {
		return new SnsBoardResponseDTO(mapper.getDetail(bno));
	}

	public void delete(int bno) {
		mapper.delete(bno);
	}

	public String searchLike(Map<String, String> params) {
		if(mapper.searchLike(params) == 0) { // count가 0일때 
			mapper.createLike(params); // 좋아요 등록
			return "like";
		} else {
			mapper.deleteLike(params);
			return "delete";
		}
	}

	public List<Integer> likeList(String userId) {
		return mapper.likeList(userId);
	}

	
}




































