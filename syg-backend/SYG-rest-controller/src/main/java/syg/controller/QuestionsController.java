package syg.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Question;
import syg.domain.ports.inbound.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionsController {
	
	@Autowired
	private QuestionService questionService;
	
    @GetMapping
    public ResponseEntity<Object> findAll() {
    	List<Question> questions = questionService.findAll();
    	if(questions.size() <= 0) {
    		return ResponseEntity.status(HttpStatus.OK).body(questions);
    	}
    	Collections.shuffle(questions);
    	return ResponseEntity.status(HttpStatus.OK).body(questions.subList(0, Math.min(questions.size(), 15)));
    }
    
    @GetMapping("/id")
    public ResponseEntity<Object> findById(@RequestParam(name = "id") Long id) {
    	try {
    		Question question = questionService.findById(id);
    		return ResponseEntity.status(HttpStatus.OK).body(question);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
    }
    @GetMapping("/category")
    public ResponseEntity<Object> findByCategory(@RequestParam(name = "categoryId") Long categoryId) {
    	List<Question> questions = questionService.findByCategory(categoryId);
    	if(questions.size() <= 0) {
    		return ResponseEntity.status(HttpStatus.OK).body(questions);
    	}
    	Collections.shuffle(questions);
    	return ResponseEntity.status(HttpStatus.OK).body(questions.subList(0, Math.min(questions.size(), 15)));    		
    }
}
