package syg.domain.wikidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

