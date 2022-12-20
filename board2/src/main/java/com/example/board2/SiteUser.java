package com.example.board2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

	//id, 사용자명, 비밀번호, 이메일
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//unique=true : 반드시 입력 받아야한다.
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Column(unique =true)
	private String email;
	
	
	
	
	
}
