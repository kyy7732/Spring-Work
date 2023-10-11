package com.spring.myweb.freeboard.dto;

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

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FreeDetailDTO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String Date;
	
	public FreeDetailDTO(FreeBoard board) {
		super();
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.Date = makePrettierDateString(fix(board.getUpdateDate(), board.getRegDate()));
	}

	private String makePrettierDateString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			return dtf.format(regDate);
	
	}
	
	private LocalDateTime fix(LocalDateTime regDate, LocalDateTime board) {
		if(regDate == null) {
			return board;
		}
			return regDate;
	}
	
	
	
	
}

















