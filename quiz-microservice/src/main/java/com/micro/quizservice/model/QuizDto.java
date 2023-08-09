package com.micro.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
	private String category;
	private int numQ;
	private String title;
}
