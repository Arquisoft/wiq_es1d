package syg.mysql.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import syg.domain.model.Question;
import syg.mysql.entities.AnswerEntity;
import syg.mysql.entities.QuestionEntity;

@Component
public class QuestionMapper {
	
	@Autowired
	private AnswerMapper answerMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	public QuestionEntity toEntity(Question question) {
		return new QuestionEntity(question.getId(), question.getText(), 60, 
				question.getCategory() != null ? categoryMapper.toEntity(question.getCategory()) : null);
	}
	
	public List<QuestionEntity> toEntity(List<Question> questions) {
		List<QuestionEntity> questionsEntity = new ArrayList<QuestionEntity>();
		for (Question question : questions) {
			questionsEntity.add(toEntity(question));
		}
		return questionsEntity;
	}
	
	public Question toDomain(QuestionEntity questionEntity) {
		return new Question(questionEntity.getId(), questionEntity.getText(), questionEntity.getTimeLimit(),
				questionEntity.getCategory() != null ? categoryMapper.toDomain(questionEntity.getCategory()) : null, 
				answerMapper.toDomain(questionEntity.getAnswers() != null ? questionEntity.getAnswers() : new ArrayList<AnswerEntity>()));
	}
	
	public List<Question> toDomain(List<QuestionEntity> questionsEntity) {
		List<Question> questions = new ArrayList<Question>();
		for (QuestionEntity questionEntity : questionsEntity) {
			questions.add(toDomain(questionEntity));
		}
		return questions;
	}
}
