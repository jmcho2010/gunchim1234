package com.example.board2;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "sign_up";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "sign_up";
		}
		//패스워드가 일치하지 않을때 처리하는 방법.
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			//rejectValue 코드를 이용해 오류를 발생시킴.
			bindingResult.rejectValue("password2", "passwordIncorrect", "2개 패스워드 일치 x");
			return "sign_up";
		}
		try {
			userService.create(userCreateForm.getUsername(), 
					userCreateForm.getEmail(), userCreateForm.getPassword1());
		} catch(DataIntegrityViolationException dive) {
			dive.printStackTrace();
			bindingResult.reject("회원가입 실패", "이미 등록되어있는 사용자입니다.");
			return "sign_up";
		}
		
		catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("회원가입 실패", e.getMessage());
			return "sign_up";
		}

		
		return "redirect:/";
		
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
	
	
}
