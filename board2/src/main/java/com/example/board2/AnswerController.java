package com.example.board2;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, 
			@RequestParam String content ,@Valid AnswerForm answerForm, 
			BindingResult bindingResult, Principal principal) throws DataNotFoundException {
		// 서버는 이 정보를 모르기때문에 직접 가져다가 어떤 질문에 저장할지를
		// 알려줄 필요가 있다 

			Question question = this.questionService.getQuestion(id);
			SiteUser siteuser = this.userService.getUser(principal.getName());
			if(bindingResult.hasErrors()) {
				model.addAttribute("question", question);
				return "question_detail";
			}
			
			this.answerService.create(question, answerForm.getContent(), siteuser);	
		return String.format("redirect:/question/detail/%s", id);
		//return ("redirect:/question/detail/%s"+ id);

	}
	
	
	public String answerModify(AnswerForm answerForm, 
			@PathVariable("id") Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		if(!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한 없음");
		}
		answerForm.setContent(answer.getContent());
		
		return "answer_form";
	}
	
	
	
	
	
	
	
	
	
	
}
