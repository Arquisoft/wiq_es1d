package syg.controller.wikidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import syg.domain.ports.inbound.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionsController {
	
	@Autowired
	private QuestionService questionService;
	
    @GetMapping("/questions")
    public void generateQuestions() {
    	questionService.generateQuestions();
    }
}
