package com.micro.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micro.questionservice.model.Question;
import com.micro.questionservice.model.QuestionWrapper;
import com.micro.questionservice.model.Response;
import com.micro.questionservice.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return ResponseEntity.ok(questionRepository.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(new ArrayList<Question>());
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return ResponseEntity.ok(questionRepository.findByCategory(category));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(new ArrayList<Question>());
	}

	public ResponseEntity<Integer> addQuestion(Question question) {
		try {
			question = questionRepository.save(question);
			return new ResponseEntity<Integer>(question.getId(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(null);
	}

	public ResponseEntity<Integer> deleteQuestion(int questionId) {
		try {
			questionRepository.deleteById(questionId);
			return ResponseEntity.ok(questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(null);
	}

	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, Integer numQ) {
		List<Integer> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
		return ResponseEntity.ok(questions);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionsIds) {
		List<QuestionWrapper> questions = questionRepository.findAllById(questionsIds).stream()
				.map(q -> new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(questions);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right = 0;
		for (Response response : responses) {
			Question question = questionRepository.findById(response.getId()).get();
			if (response.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
		}
		return ResponseEntity.ok(right);
	}

}
