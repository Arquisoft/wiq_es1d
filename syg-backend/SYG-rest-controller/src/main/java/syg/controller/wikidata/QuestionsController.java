package syg.controller.wikidata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.model.Question;
import syg.domain.ports.inbound.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
	
	@Autowired
	private QuestionService questionService;
	
    @GetMapping
    public ResponseEntity<List<Question>> findAll() {
    	List<Question> questions = questionService.findAll();
    	return ResponseEntity.status(HttpStatus.OK).body(questions);
    }
    
    @GetMapping("/id")
    public ResponseEntity<Question> findById(@RequestParam(name = "id") Long id) {
    	Question question = questionService.findById(id);
    	return ResponseEntity.status(HttpStatus.OK).body(question);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Question>> findByCategory(@RequestParam(name = "categoryId") Long categoryId) {
    	List<Question> questions = questionService.findByCategory(categoryId);
    	return ResponseEntity.status(HttpStatus.OK).body(questions);
    }
    
    @GetMapping("/generate")
    public void generateQuestions() {
    	questionService.generateQuestions();
    }
}
