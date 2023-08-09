package com.micro.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.quizservice.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
