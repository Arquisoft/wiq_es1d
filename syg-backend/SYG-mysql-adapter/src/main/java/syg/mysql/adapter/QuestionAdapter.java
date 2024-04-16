package syg.mysql.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Answer;
import syg.domain.model.Question;
import syg.domain.model.WikiData;
import syg.domain.model.enums.CategoryEnum;
import syg.domain.ports.outbounds.QuestionPersistence;
import syg.mysql.entities.AnswerEntity;
import syg.mysql.entities.CategoryEntity;
import syg.mysql.entities.QuestionEntity;
import syg.mysql.mapper.AnswerMapper;
import syg.mysql.mapper.QuestionMapper;
import syg.mysql.repositories.CategoryRepository;
import syg.mysql.repositories.QuestionRepository;

@Component
public class QuestionAdapter implements QuestionPersistence {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private QuestionMapper questionMapper;

	@Autowired
	private AnswerMapper answerMapper;

	@Autowired
	private WikiData wikiData;

	@Override
	public List<Question> findAll() {
		return questionMapper.toDomain(questionRepository.findAll());
	}

	@Override
	public Question findById(Long id) {
		Optional<QuestionEntity> optionalQuestion = questionRepository.findById(id);
		if (!optionalQuestion.isPresent()) {
			throw new NotFoundException("The question with id " + id + " does not exist");
		}
		return questionMapper.toDomain(questionRepository.findById(id).get());
	}

	@Override
	public List<Question> findByCategory(Long categoryId) {
		return questionMapper.toDomain(questionRepository.findByCategory_Id(categoryId));
	}
	
	@Override
	public void deleteQuestions() {
		questionRepository.deleteAll();
	}

	@Override
	public void generatedQuestions() {
		List<CategoryEntity> categoriesEntities = categoryRepository.findAll();
		for (CategoryEnum categoryEnum : CategoryEnum.values()) {
			CategoryEntity category = null;
			for (CategoryEntity categoryEntity : categoriesEntities) {
				if (categoryEntity.getName().equals(categoryEnum.getLabel())) {
					category = categoryEntity;
					break;
				}
			}

			String sparqlQuery = String.format(WikiData.WIKIDATA_QUERY, categoryEnum.getValue());
			List<WikiData> responses = wikiData.executeSparqlQuery(sparqlQuery);

			List<QuestionEntity> questions = generatedQuestionsLogic(responses, category);
			questionRepository.saveAll(questions);
		}
	}

	/**
	 * Genera las preguntas, con sus respuestas incorrecta.
	 * Las forma de manera totalmente al azar, para que no se repitan nunca.
	 * 
	 * @param wikisQuestions Las preguntas generadas
	 * @param categoryEntity La categoria a la que pertencen
	 * @return
	 */
	private List<QuestionEntity> generatedQuestionsLogic(List<WikiData> wikisQuestions, CategoryEntity categoryEntity) {
		List<QuestionEntity> questionsGenerated = new ArrayList<QuestionEntity>();
		for (int i = 0; i < wikisQuestions.size(); i++) {
			Question question = new Question(wikisQuestions.get(i).getDescription().substring(0, 1).toUpperCase()
					+ wikisQuestions.get(i).getDescription().substring(1));
			List<Answer> answers = new ArrayList<Answer>();
			Answer correctAnswer = new Answer(wikisQuestions.get(i).getResponse().substring(0, 1).toUpperCase()
					+ wikisQuestions.get(i).getResponse().substring(1), true);
			
			List<Integer> incorrectResponses = wikiData.generateUniqueRandomIndex(wikisQuestions.size(), i, 3);
			for (Integer incorrectResponseIndex : incorrectResponses) {
				answers.add(new Answer(wikisQuestions.get(incorrectResponseIndex).getResponse(), false));
			}
			answers.add(correctAnswer);

			QuestionEntity questionEntity = questionMapper.toEntity(question);
			questionEntity.setCategory(categoryEntity);
			List<AnswerEntity> answersEntities = answerMapper.toEntity(answers);
			answersEntities.stream().forEach(answer -> answer.setQuestion(questionEntity));
			questionEntity.setAnswers(answersEntities);
			questionsGenerated.add(questionEntity);
		}
		return questionsGenerated;
	}
}
