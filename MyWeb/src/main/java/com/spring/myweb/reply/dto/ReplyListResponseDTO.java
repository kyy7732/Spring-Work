package com.spring.myweb.reply.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.reply.entity.Reply;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter @ToString @EqualsAndHashCode
public class ReplyListResponseDTO {

	private int replyNo;
	private String replyWriter;
	private String replyText;
	private String date;
	
	public ReplyListResponseDTO(Reply reply) {
		this.replyNo = reply.getReplyNo();
		this.replyWriter = reply.getReplyWriter();
		this.replyText = reply.getReplyText();
		if(reply.getUpdateDate() == null) {
			this.date = makePrettierDateString(reply.getReplyDate());
		} else {
			this.date = makePrettierDateString(reply.getUpdateDate()) + " (수정됨)";
		}
	}
	
	
	static String makePrettierDateString(LocalDateTime regDate) { //DTO 패키지 안에서만 사용가능하게 default값으로
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(regDate);
	}

}
