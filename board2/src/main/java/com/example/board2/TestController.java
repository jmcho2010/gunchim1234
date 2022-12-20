package com.example.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class TestController {
	
	private final QuestionService questionService;
	private final UserService userService;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		// @RequestParam(value="page", defaultValue="0") 
		//  -> url에 페이지 파라미터 page가 전달되지 않는다면 디폴트값으로 0을 설정.
		//List<Question> questionList = this.questionService.getList();
		Page<Question> paging = this.questionService.getList(page);
		//model.addAttribute("questionList", questionList);
		model.addAttribute("paging", paging);
		return "question_list";
	}
	
	@RequestMapping(value="/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) throws Exception {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(QuestionForm questionForm) {
		return "question_create";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String create(@Valid QuestionForm questionForm, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteuser = this.userService.getUser(principal.getName());
		this.questionService.create(
				questionForm.getSubject(), 
				questionForm.getContent(),
				siteuser);
		return "redirect:/question/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String modify(QuestionForm questionForm, 
			@PathVariable("id") Integer id, Principal principal) {
		//서비스로 넘겨서 수정내용을 저장.
		Question gunchim = this.questionService.getQuestion(id);
		// 수정권한을 2차로 체크.
		// 글쓴이와 로그인한 사용자가 다르다면
		if(!gunchim.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
		}
		questionForm.setSubject(gunchim.getSubject());
		questionForm.setContent(gunchim.getContent());
		return "question_modify";
		// 세팅할거냐?
		// 리턴처리 어떻게?
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modify(@Valid QuestionForm questionForm, @PathVariable("id") Integer id, 
			BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
		}
		this.questionService.modify(question, 
				questionForm.getSubject(), questionForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}
	@RequestMapping("/hell")
	@ResponseBody
	public String Hell() {
		return "test222";
	}
	
	@GetMapping("/")
	public String root() {
		System.out.println("check");
		return "redirect:/question/list";
	}
	
	
	
	
	
	
	
	
	
	
	

}
