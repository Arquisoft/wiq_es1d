package syg.bootstrap.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import syg.domain.ports.inbound.QuestionService;

@Component
public class BootstrapRunner implements ApplicationRunner {

	@Autowired 
	private QuestionService questionService;
	
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	questionService.deleteQuestions();
    	questionService.generateQuestions();
    }
}