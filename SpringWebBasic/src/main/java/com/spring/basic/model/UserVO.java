package com.spring.basic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO {

	// join.jsp에 name과 똑같은 이름을 사용해야함

	private String userId;
	private String userPw;
	private String userName;
	private List<String> hobby;
	
}
