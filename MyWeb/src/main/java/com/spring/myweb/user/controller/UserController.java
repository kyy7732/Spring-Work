package com.spring.myweb.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.user.dto.UserJoinRequestDTO;
import com.spring.myweb.user.service.UserService;
import com.spring.myweb.util.MailSenderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	private final MailSenderService mailService;
	
	// 회원가입 페이지로 이동
	@GetMapping("/userJoin")
	public void userJoin() {}
	
	// 아이디 중복 확인(비동기)
	/*
    @PathVariable은 URL 경로에 변수를 포함시켜 주는 방식
    null이나 공백이 들어갈 수 있는 파라미터라면 적용하지 않는 것을 추천
    파라미터 값에 .이  포함되어있다면 .뒤의 값은 잘린다는 것을 알아두세요.
    {}안에 변수명을 지어주시고, @PathVariable 괄호 안에 영역을 지목해서
    값을 받아옵니다.
    */
	
	@GetMapping("/id/{account}") // {} 공간에 이름짓기  
	@ResponseBody // 클라이언트 화면에 리턴값 보내기
	public String idCheck(@PathVariable String account) { // {account}에 String account로 받겠다 @pathVariable("account") String account 로 하면 괄호 생략가능
		System.out.println("클라이언트로부터 전달된 아이디: " + account);
		int result = service.idCheck(account);
		if(result == 1) return "duplicated";
		else return "ok"; // then이라는 함수에 여러가지 정보가 담겨있는데 .text()라는 함수로 이 리턴값을 받아옴
	}
	
	// 이메일 인증
	@PostMapping("/email")
	@ResponseBody
	public String mailCheck(@RequestBody String email) { // 받는 형태가 JSON이어서 java형태로 바꿔준다
		System.out.println("이메일 인증 요청 들어옴: " + email);
		// 화면단으로 인증번호를 전달.
		return mailService.joinEmail(email); // 인증번호를 받아옴 (인증번호를 문자열로 바꿧음)
	}
	
	// 회원 가입 처리
	@PostMapping("/join")
	public String join(UserJoinRequestDTO dto, RedirectAttributes ra) {
		service.join(dto);
		/*
		 redirect 상황에서 model 객체를 사용하면 데이터가 제대로 전달되지 않습니다.
		 model 객체가 forward 상황에서 사용하는 request의 대체제이기 때문에
		 redirect를 통해 응답이 나갔다가 재 요청이 들어오는 상황에서는 데이터가 소멸합니다.
		 (parameter에 노출되어 전달됨)
		 
		  redirect 상황에서 일회성으로 데이터를 전송할 때 사용하는 메서드 addFlashAttribute(name, value)
		  데이터가 url에 노출되지 않고, 한 번 이용한 후에는 알아서 소멸합니다.
		 */
		ra.addFlashAttribute("msg", "joinSuccess"); // msg라는 이름으로 joinSuccess를 보냄
		return "redirect:/user/userLogin"; // 로그인 화면을 열어줘
		// redirect:를 사용할땐 model로 보내기는 불가
		// model = request대신 사용하는것 응답이 나가면 들어있던 데이터는 소멸,  
	}
	
	
	// 로그인 페이지로 이동 요청
	@GetMapping("/userLogin")
	public void login() {}
	
	// 로그인 요청
	@PostMapping("/userLogin") // userLogin.jsp에 action값이 없으므로 현재있는 경로로 다시 요청 처리
	public void login(String userId, String userPw, Model model) {
		System.out.println("나는 UserController의 login이다!!!!");
		model.addAttribute("result", service.login(userId, userPw));
	}
	
	// 마이페이지 이동 요청
	@GetMapping("/userMypage")
	public void userMypage(HttpSession session, Model model) {
		// 마이페이지는 로그인 한 사람만 이동 가능 -> 세션에 아이디가 있다!
		String id = (String) session.getAttribute("login"); 
		model.addAttribute("userInfo", service.getInfo(id));
		
		
	}
	
}































