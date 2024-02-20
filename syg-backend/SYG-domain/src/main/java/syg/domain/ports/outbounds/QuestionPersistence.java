package syg.domain.ports.outbounds;

import java.util.List;

import syg.domain.model.Question;

public interface QuestionPersistence {

	public List<Question> findAll();
	
	public Question findById(Long id);
	
	public List<Question> findByCategory(Long categoryId);

	public void generatedQuestions();
	
}
