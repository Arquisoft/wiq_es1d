package syg.domain.ports.inbound;

import java.util.List;

import syg.domain.model.Question;

public interface QuestionService {
	
	public List<Question> findAll();
	
	public Question findById(Long id);
	
	public List<Question> findByCategory(Long categoryId);

	public void generateQuestions();

}
