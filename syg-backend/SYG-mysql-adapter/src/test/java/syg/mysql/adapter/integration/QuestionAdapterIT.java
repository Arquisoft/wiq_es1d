package syg.mysql.adapter.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Question;
import syg.mysql.SYGdbContainerIT;
import syg.mysql.adapter.QuestionAdapter;
import syg.mysql.configuration.IntegrationTest;

@IntegrationTest
public class QuestionAdapterIT extends SYGdbContainerIT {

	@Autowired
	private QuestionAdapter questionAdapter;

	@Test
	@DisplayName("Se buscan todas las preguntas en base de datos")
	void find_all_questions() {
		List<Question> questions = questionAdapter.findAll();
		assertEquals(8, IterableUtil.sizeOf(questions));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	void find_question_by_id() {
		Question question = questionAdapter.findById(1L);
		
		assertEquals(1, question.getId());
		assertEquals("¿Cual es el animal que no puede saltar?", question.getText());
		assertEquals(60, question.getTimeLimit());
		assertEquals(1, question.getCategory().getId());
		assertEquals("animales", question.getCategory().getName());
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id que no existe")
	void find_question_by_not_exist_id() {
		final Executable exec;
		
		exec = () -> questionAdapter.findById(50L);
		
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria")
	void find_questions_by_category() {
		List<Question> questions = questionAdapter.findByCategory(1L);
		
		assertEquals(3, IterableUtil.sizeOf(questions));
		assertEquals(1, questions.get(0).getId());
		assertEquals("¿Cual es el animal que no puede saltar?", questions.get(0).getText());
		assertEquals(60, questions.get(0).getTimeLimit());
		assertEquals(1, questions.get(0).getCategory().getId());
		assertEquals("animales", questions.get(0).getCategory().getName());
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria que no existe")
	void find_question_by_not_exist_category() {
		List<Question> questions = questionAdapter.findByCategory(100L);
		
		assertEquals(0, IterableUtil.sizeOf(questions));
	}
}
