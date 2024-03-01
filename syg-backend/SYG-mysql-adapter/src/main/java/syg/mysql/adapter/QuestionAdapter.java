package syg.mysql.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wikidata.wdtk.datamodel.helpers.Datamodel;
import org.wikidata.wdtk.datamodel.interfaces.EntityDocument;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.wikibaseapi.BasicApiConnection;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Answer;
import syg.domain.model.Question;
import syg.domain.ports.outbounds.QuestionPersistence;
import syg.mysql.entities.AnswerEntity;
import syg.mysql.entities.QuestionEntity;
import syg.mysql.mapper.AnswerMapper;
import syg.mysql.mapper.QuestionMapper;
import syg.mysql.repositories.AnswerRepository;
import syg.mysql.repositories.QuestionRepository;

@Component
public class QuestionAdapter implements QuestionPersistence {

	private WikibaseDataFetcher wikidataDB = new WikibaseDataFetcher(BasicApiConnection.getWikidataApiConnection(), Datamodel.SITE_WIKIDATA);
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private AnswerMapper answerMapper;
			
	@Override
	public List<Question> findAll() {
		return questionMapper.toDomain(questionRepository.findAll());
	}

	@Override
	public Question findById(Long id) {
		Optional<QuestionEntity> optionalQuestion =  questionRepository.findById(id);
		if(!optionalQuestion.isPresent()) {
			throw new NotFoundException("The question with id " + id + " does not exist");
		}
		return questionMapper.toDomain(questionRepository.findById(id).get());
	}

	@Override
	public List<Question> findByCategory(Long categoryId) {
		return questionMapper.toDomain(questionRepository.findByCategory_Id(categoryId));
	}
	
	public void generatedQuestions() {
		try {
			Question question = getQuestionFromWikiData("Q43");
			Answer correctAnswer = getCorrectAnswerFromWikiData("Q43");
			List<Answer> incorrectAnswers = getInCorrectAnswerFromWikiData("Q43");
			
			List<Answer> allAnswers = incorrectAnswers;
			allAnswers.add(correctAnswer);
			
			QuestionEntity questionEntity = questionMapper.toEntity(question);
			questionEntity.setAnswers(answerMapper.toEntity(allAnswers));
			QuestionEntity questionEntityResponse = questionRepository.save(questionEntity);
			
			for (AnswerEntity answerEntity : questionEntityResponse.getAnswers()) {
			    answerEntity.setQuestion(questionEntityResponse);
			}
			
			answerRepository.saveAll(questionEntityResponse.getAnswers());
		} catch (Exception e) {
			throw new NullPointerException(e.getMessage()); //A cambiar por excepción personalizada
		}
	}
	
	private Question getQuestionFromWikiData(String wikiQuestion){
		try {
			EntityDocument entityDocument = wikidataDB.getEntityDocument(wikiQuestion);
			ItemDocument itemDocument = (ItemDocument) entityDocument;
			String question = itemDocument.getDescriptions().get("es").getText();
			return new Question(question);
		} catch (Exception e) {
			throw new NullPointerException(); //A cambiar por excepción personalizada
		}
	}
	
	private Answer getCorrectAnswerFromWikiData(String wikiQuestion){
		try {
			EntityDocument entityDocument = wikidataDB.getEntityDocument(wikiQuestion);
			ItemDocument itemDocument = (ItemDocument) entityDocument;
			String correctAnswer = itemDocument.getLabels().get("es").getText();
			return new Answer(null, correctAnswer, true);
		} catch (Exception e) {
			throw new NullPointerException(); //A cambiar por excepción personalizada
		}
	}
	
	private List<Answer> getInCorrectAnswerFromWikiData(String wikiQuestion){
		try {
			List<Answer> incorrectAnswers = new ArrayList<Answer>();
			for (int i = 1; i < 4; i++) {
				EntityDocument entityDocument = wikidataDB.getEntityDocument(wikiQuestion + i);
				ItemDocument itemDocument = (ItemDocument) entityDocument;
				String incorrectAnswer = itemDocument.getLabels().get("es").getText();
				incorrectAnswers.add(new Answer(null, incorrectAnswer, false));				
			}
			if(incorrectAnswers.size() == 3) {
				return incorrectAnswers;				
			}
			throw new NullPointerException(); //A cambiar por excepción personalizada
		} catch (Exception e) {
			throw new NullPointerException(); //A cambiar por excepción personalizada
		}
	}
}
