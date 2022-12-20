package com.example.board2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository q;
	
	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.q.findAll(pageable);
	}
	
	public Question getQuestion(Integer id) throws DataNotFoundException {
		Optional<Question> question = this.q.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("질문이 엄서");
		}
	}

	public void create(String subject, String content, SiteUser user) {
		// 리파지토리를 통해 H2 데이터 베이스로 파라미터들을 저장시키는 코드를 작성해보세용
		Question q1 = new Question();
		q1.setSubject(subject);
		q1.setContent(content);
		q1.setCreateDate(LocalDateTime.now());
		q1.setAuthor(user);
		this.q.save(q1);		
	}
	
	public void modify(Question question, String subject, String content) {
		// 파라미터 3개의 이유.
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.q.save(question);
		
		
	}
	
	
}
