package com.spring.myweb.freeboard.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.cglib.core.Local;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FreeDetailDTO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String date;
	
	public FreeDetailDTO(FreeBoard board) {
		super();
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
//		if(board.getUpdateDate() == null) { 선생님 작성 방법
//			this.date = FreeListResponseDTO.makePrettierDateString(board.getRegDate());
//		} else {
//			this.date = FreeListResponseDTO.makePrettierDateString(board.getUpdateDate()) + " (수정됨)";
//		}
		
		if(fix(board.getUpdateDate(), board.getRegDate()).equals(board.getRegDate())) {
			this.date = FreeListResponseDTO.makePrettierDateString(fix(board.getUpdateDate(), board.getRegDate()));
		} else {
			this.date = FreeListResponseDTO.makePrettierDateString(fix(board.getUpdateDate(), board.getRegDate())) + "(수정됨)";
		}
	}
	
	private LocalDateTime fix(LocalDateTime update, LocalDateTime regDate) {
		if(update == null) {
			return regDate;
		}
			return update;
	}
	
	
	
	
}

















