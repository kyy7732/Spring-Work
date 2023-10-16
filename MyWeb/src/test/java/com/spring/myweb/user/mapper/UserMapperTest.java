package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.user.entity.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper;
	
	@Test
	@DisplayName("회원 가입을 진행했을 때 회원가입이 성공해야 한다.")
	void registTest() {
		User user = User.builder()
				.userId("kim1234")
				.userPw("kkk12")
				.userName("김철수")
//				.userPhone1("010")
//				.userPhone2("12345678")
				.userEmail1("chun1234")
				.userEmail2("naver.com")
//				.addrBasic("1234")
//				.addrDetail("춘식동")
//				.addrZipNum("21114")
				.build();
		mapper.join(user);
		assertTrue(user.getUserId().equals(user.getUserId()));
	}

	@Test
	@DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴되어야 한다.")
	void checkIdTest() {
		String id = "kim1234";
		
		assertEquals(1, mapper.idCheck(id)); // count로 조회를 하므로 아이디를찾으면 1 아니면 0
	}
	
	@Test
	@DisplayName("존재하는 회원 아이디를 입력 했을 시 그 회원의 비밀번호가 리턴되어야 한다.")
	void loginTest() {
		String id = "kim1234";
		
		assertNotNull(mapper.login(id)); // usermapper를 통해 값을 받아옴
		assertEquals("kkk12", mapper.login(id));
	}
	
	@Test
	@DisplayName("존재하지 않는 회원의 아이디를 전달하면 null이 올 것이다.")
	void getInfoTest() {
		assertNull(mapper.getInfo("merong"));
	}
	
	@Test
	@DisplayName("id를 제외한 회원의 정보를 수정할 수 있어야 한다.")
	void updateTest() {
		
		User user = User.builder()
						.userId("kim1234")
						.userPw("aaa1111!")
						.userName("김수정")
						.userPhone1("010")
						.build();
		
		mapper.updateUser(user);	
		
		assertEquals(user.getUserName(), mapper.getInfo("kim1234").getUserName());
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
