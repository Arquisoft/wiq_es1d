package syg.mysql.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.model.Question;
import syg.mysql.configuration.UnitAdapterTest;
import syg.mysql.entities.QuestionEntity;

@UnitAdapterTest
public class QuestionMapperTests {

	@Autowired
	private QuestionMapper questionMapper;
	
	@Test
	@DisplayName("Se mapea un objeto user de dominio a entidad")
	void model_to_entity() {
		QuestionEntity questionEntity = questionMapper.toEntity(new Question(1L, "Pregunta 1", 60, null, new ArrayList<>()));
		
		assertEquals(1L, questionEntity.getId());
		assertEquals("Pregunta 1", questionEntity.getText());
		assertEquals(60, questionEntity.getTimeLimit());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos users de dominio a entidad")
	void model_to_entity_list() {
		List<Question> questions = new ArrayList<Question>();
		questions.add(new Question(1L, "Pregunta 1", 60, null, new ArrayList<>()));
		questions.add(new Question(2L, "Pregunta 2", 70, null, new ArrayList<>()));
		questions.add(new Question(3L, "Pregunta 3", 65, null, new ArrayList<>()));
		
		List<QuestionEntity> questionsEntity = questionMapper.toEntity(questions);
		
		assertEquals(3, questionsEntity.size());
		assertEquals(1L, questionsEntity.get(0).getId());
		assertEquals("Pregunta 1", questionsEntity.get(0).getText());
		assertEquals(60, questionsEntity.get(0).getTimeLimit());
	}
	
	@Test
	@DisplayName("Se mapea un objeto user de entidad a dominio")
	void entity_to_model() {
		Question question = questionMapper.toDomain(new QuestionEntity(1L, "Pregunta 1", 60, null, new ArrayList<>()));
		
		assertEquals(1L, question.getId());
		assertEquals("Pregunta 1", question.getText());
		assertEquals(60, question.getTimeLimit());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos users de entidad a dominio")
	void entity_to_model_list() {
		List<QuestionEntity> questionsEntity = new ArrayList<QuestionEntity>();
		questionsEntity.add(new QuestionEntity(1L, "Pregunta 1", 60, null, new ArrayList<>()));
		questionsEntity.add(new QuestionEntity(2L, "Pregunta 2", 70, null, new ArrayList<>()));
		questionsEntity.add(new QuestionEntity(3L, "Pregunta 3", 65, null, new ArrayList<>()));
		
		List<Question> questions = questionMapper.toDomain(questionsEntity);
		
		assertEquals(3, questions.size());
		assertEquals(1L, questions.get(0).getId());
		assertEquals("Pregunta 1", questions.get(0).getText());
		assertEquals(60, questions.get(0).getTimeLimit());
	}
}
