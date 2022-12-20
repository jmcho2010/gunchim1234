package com.example.board2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	//Pageable 객체를 입력받아 Page<Question> 타입 객체를 리턴하는 findAll 메소드 생성.
	Page<Question> findAll(Pageable pageable);
		
	
}
