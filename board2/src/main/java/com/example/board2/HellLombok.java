package com.example.board2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HellLombok {

	private String hell;
	private int lombok;
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HellLombok hell = new HellLombok();
		hell.setHell("test11234");
		hell.setLombok(666);
		
		System.out.println(hell.getHell());
		System.out.println(hell.getLombok());
	}

}
