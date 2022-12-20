package com.example.board2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason ="entity not found")
public class DataNotFoundException extends RuntimeException{
	// 직렬화 : 자바의 객체를 바이트 배열로 변환하여 파일, 메모리, DB등이 저장하는 과정.
	// 값의 체크를 위해 사용.
	private static final long serialVersionUID= 1L; // 직렬화
	public DataNotFoundException(String message) {
		super(message);
	}
}
