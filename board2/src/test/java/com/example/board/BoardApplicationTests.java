package com.example.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board2.QuestionService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	//private QuestionRepository q;
	private QuestionService q;
	
//	@Test
//	void contextLoads() {
////		Question q1 = new Question();
////		q1.setSubject("아오 개춥네");
////		q1.setContent("뭐이리 춥습니까");
////		q1.setCreateDate(LocalDateTime.now());
////		this.q.save(q1);
////		
////		// 제목 : 조준모 봐라
////		// 내용 : 너 몇살이냐 
////		// 라는 게시글을 저장할거에요~
////		// 아래에 작성해주세요.
////		Question q2 = new Question();
////		q2.setSubject("조준모 봐라");
////		q2.setContent("너 몇살이냐");
////		q2.setCreateDate(LocalDateTime.now());
////		this.q.save(q2);
//		
//		// findAll : 데이터 조회.
//		List<Question> all = this.q.findAll();
//		//assertEquals : (기대값, 실제값)
//		
//		
//		//assertEquals(2, all.size()); 
//		
//		
//		
//	}

	@Test
	void jpaTest() {
		// findById : id값을 기준으로 데이터를 조회.
		// id값을 조회하기위해 2개의 인스턴스가 필요.
		// 1. 조회한 결과를 받아올 인스턴스.
		// 2. 대조할 데이터를 입력받을 인스턴스.
		//  -> Null 처리 때문에 Optional 이라는 클래스를 써야하기 때문.
		
//		Optional<Question> oq = this.q.findById(1);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assert("아오 개춥네", q.getSubject());
//		}
//		Question q1 = new Question();
//        for (int i = 1; i <= 300; i++) {
//            String subject = String.format("테스트 데이터입니다:[%03d]", i);
//            String content = "내용무";
//            this.q.create(subject, content, "군침");
//        } 
		
	}
	


}
