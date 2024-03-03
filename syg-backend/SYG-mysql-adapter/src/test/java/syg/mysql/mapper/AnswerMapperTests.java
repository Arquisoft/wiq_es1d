package syg.mysql.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.model.Answer;
import syg.mysql.configuration.UnitAdapterTest;
import syg.mysql.entities.AnswerEntity;

@UnitAdapterTest
public class AnswerMapperTests {
	
	@Autowired
	private AnswerMapper answerMapper;
	
	@Test
	@DisplayName("Se mapea un objeto respuesta de dominio a entidad")
	void model_to_entity() {
		AnswerEntity answerEntity = answerMapper.toEntity(new Answer(1L, "Respuesta 1", true));
		
		assertEquals(1L, answerEntity.getId());
		assertEquals("Respuesta 1", answerEntity.getText());
		assertEquals(true, answerEntity.getIsCorrect());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos respuesta de dominio a entidad")
	void model_to_entity_list() {
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(1L, "Respuesta 1", true));
		answers.add(new Answer(2L, "Respuesta 2", false));
		answers.add(new Answer(3L, "Respuesta 3", false));
		
		List<AnswerEntity> answersEntity = answerMapper.toEntity(answers);
		
		assertEquals(3, answersEntity.size());
		assertEquals(1L, answersEntity.get(0).getId());
		assertEquals("Respuesta 1", answersEntity.get(0).getText());
		assertEquals(true, answersEntity.get(0).getIsCorrect());
	}
	
	@Test
	@DisplayName("Se mapea un objeto respuesta de entidad a dominio")
	void entity_to_model() {
		Answer answer = answerMapper.toDomain(new AnswerEntity(1L, "Respuesta 1", true));
		
		assertEquals(1L, answer.getId());
		assertEquals("Respuesta 1", answer.getText());
		assertEquals(true, answer.getIsCorrect());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos respuesta de entidad a dominio")
	void entity_to_model_list() {
		List<AnswerEntity> answersEntity = new ArrayList<AnswerEntity>();
		answersEntity.add(new AnswerEntity(1L, "Respuesta 1", true));
		answersEntity.add(new AnswerEntity(2L, "Respuesta 2", false));
		answersEntity.add(new AnswerEntity(3L, "Respuesta 3", false));
		
		List<Answer> answers = answerMapper.toDomain(answersEntity);
		
		assertEquals(3, answers.size());
		assertEquals(1L, answers.get(0).getId());
		assertEquals("Respuesta 1", answers.get(0).getText());
		assertEquals(true, answers.get(0).getIsCorrect());
	}
}
