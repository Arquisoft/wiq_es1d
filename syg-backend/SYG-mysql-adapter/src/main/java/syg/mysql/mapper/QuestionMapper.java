package syg.mysql.mapper;

import org.springframework.stereotype.Component;

import syg.domain.model.Question;
import syg.mysql.entities.QuestionEntity;

@Component
public class QuestionMapper {

	public QuestionEntity toEntity(Question question) {
		return new QuestionEntity(question.getText(), 60, null);
	}
}
