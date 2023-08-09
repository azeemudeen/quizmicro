package com.micro.quizservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micro.quizservice.feign.QuizInterface;
import com.micro.quizservice.model.QuestionWrapper;
import com.micro.quizservice.model.Quiz;
import com.micro.quizservice.model.Response;
import com.micro.quizservice.repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuizInterface quizInterface;
	
	public ResponseEntity<Integer> createQuiz(String category, int numQ, String title) {
		List<Integer> questions = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questions);
		quiz = quizRepository.save(quiz);
		return new ResponseEntity<Integer>(quiz.getId(),HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Quiz quiz = quizRepository.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionsIds();
		List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsById(questionIds).getBody();
		return ResponseEntity.ok(questionsForUser);
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		Quiz quiz = quizRepository.findById(id).get();
//		List<Question> questions = quiz.getQuestions();
		int right = 0;
//		for(Response response: responses) {
//			for(Question question: questions) {
//				if(response.getResponse().equals(question.getRightAnswer())) {
//					right++;
//				}				
//			}
//		}
		return ResponseEntity.ok(right);
	}
}
