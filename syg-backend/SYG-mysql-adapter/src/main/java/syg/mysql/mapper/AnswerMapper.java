package syg.mysql.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import syg.domain.model.Answer;
import syg.mysql.entities.AnswerEntity;

@Component
public class AnswerMapper {

	public AnswerEntity toEntity(Answer answer) {
		return new AnswerEntity(answer.getId(), answer.getText(), answer.getIsCorrect());
	}
	
	public List<AnswerEntity> toEntity(List<Answer> answers) {
		List<AnswerEntity> answersEntity = new ArrayList<AnswerEntity>();
		for (Answer answer : answers) {
			answersEntity.add(toEntity(answer));
		}
		return answersEntity;
	}
	
	public Answer toDomain(AnswerEntity answerEntity) {
		return new Answer(answerEntity.getId(), answerEntity.getText(), answerEntity.getIsCorrect());
	}
	
	public List<Answer> toDomain(List<AnswerEntity> answersEntity) {
		List<Answer> answers = new ArrayList<Answer>();
		for (AnswerEntity answerEntity : answersEntity) {
			answers.add(toDomain(answerEntity));
		}
		return answers;
	}
}
