package com.spring.basic.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.basic.board.dto.BoardListResponseDTO;
import com.spring.basic.board.entity.Board;
import com.spring.basic.board.repository.IBoardMapper;
import com.spring.basic.score.entity.Score;

import lombok.RequiredArgsConstructor;

@Service // 빈등록
@RequiredArgsConstructor
public class BoardService {

	private final IBoardMapper mapper;

	public void insertArticle(String writer, String title, String content) {
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		mapper.insertArticle(board);
	}

	public List<BoardListResponseDTO> getArticles() {
		List<BoardListResponseDTO> dtoList = new ArrayList<>();
		List<Board> boardList = mapper.getArticles();
		for(Board b : boardList) {
			BoardListResponseDTO dto = new BoardListResponseDTO(b);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public Board retrieve(int boardNo) {
		return mapper.getArticle(boardNo);
	}

	public void modify(int boardNo, String writer, String title, String content) {
//		
//		Board board = mapper.getArticle(boardNo);
//		board.setWriter(writer);
//		board.setTitle(title);
//		board.setContent(content);
//		mapper.updateArticle(board);
//		
		
		// builder패턴을 사용함 가독성이 좋다
		mapper.updateArticle(Board.builder()
									.boardNo(boardNo)
									.writer(writer)
									.title(title)
									.content(content)
									.build());
		
	}

	public void delete(int boardNo) {
		mapper.deleteArticle(boardNo);
	}

	

	
	
	
}


























