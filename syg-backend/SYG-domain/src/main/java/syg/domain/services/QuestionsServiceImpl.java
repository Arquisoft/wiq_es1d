package syg.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import syg.domain.model.Question;
import syg.domain.ports.inbound.QuestionService;
import syg.domain.ports.outbounds.QuestionPersistence;

@Service
public class QuestionsServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionPersistence questionPersistence;
	
	@Override
	public void generateQuestions() {
		questionPersistence.generatedQuestions();
	}

	@Override
	public List<Question> findAll() {
		return questionPersistence.findAll();
	}

	@Override
	public Question findById(Long id) {
		return questionPersistence.findById(id);
	}

	@Override
	public List<Question> findByCategory(Long categoryId) {
		return questionPersistence.findByCategory(categoryId);
	}
}

