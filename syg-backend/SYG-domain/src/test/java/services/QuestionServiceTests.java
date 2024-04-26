package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import configuration.UnitDomainTest;
import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.domain.model.Question;
import syg.domain.ports.inbound.QuestionService;
import syg.domain.ports.outbounds.QuestionPersistence;

@UnitDomainTest
public class QuestionServiceTests {
	
	@Autowired
	private QuestionService questionService;
	
	@MockBean
	private QuestionPersistence questionPersistence;
	
	@Test
	@DisplayName("Se buscan todas las preguntas en base de datos")
	void find_all_questions() {
		List<Question> questionResponse = new ArrayList<Question>();
		questionResponse.add(new Question(1L, "Pregunta 1", 60, new Category(1L, "animales"), new ArrayList<>()));
		questionResponse.add(new Question(2L, "Pregunta 2", 60, new Category(2L, "deportes"), new ArrayList<>()));
		questionResponse.add(new Question(3L, "Pregunta 3", 60, new Category(3L, "plantas"), new ArrayList<>()));
		
		when(questionPersistence.findAll()).thenReturn(questionResponse);
		
		List<Question> questions = questionService.findAll();
		assertEquals(3, IterableUtil.sizeOf(questions));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	void find_question_by_id() {
		Question questionResponse = new Question(1L, "Pregunta 1", 60, new Category(1L, "animales"), new ArrayList<>());
		
		when(questionPersistence.findById(1L)).thenReturn(questionResponse);
		
		Question question = questionService.findById(1L);
		assertEquals(1, question.getId());
		assertEquals("Pregunta 1", question.getText());
		assertEquals(60, question.getTimeLimit());
		assertEquals(1, question.getCategory().getId());
		assertEquals("animales", question.getCategory().getName());
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id que no existe")
	void find_question_by_not_exist_id() {
		final Executable exec;
		
		when(questionPersistence.findById(50L)).thenThrow(NotFoundException.class);
		
		exec = () -> questionService.findById(50L);
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria")
	void find_questions_by_category() {
		List<Question> questionResponse = new ArrayList<Question>();
		questionResponse.add(new Question(1L, "Pregunta 1", 60, new Category(1L, "animales"), new ArrayList<>()));
		questionResponse.add(new Question(5L, "Pregunta 5", 60, new Category(1L, "animales"), new ArrayList<>()));
		
		when(questionPersistence.findByCategory(1L)).thenReturn(questionResponse);
		
		List<Question> questions = questionService.findByCategory(1L);
		assertEquals(2, IterableUtil.sizeOf(questions));
		assertEquals(1, questions.get(0).getId());
		assertEquals("Pregunta 1", questions.get(0).getText());
		assertEquals(60, questions.get(1).getTimeLimit());
		assertEquals(1, questions.get(1).getCategory().getId());
		assertEquals("animales", questions.get(1).getCategory().getName());
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria que no existe")
	void find_question_by_not_exist_category() {
		List<Question> questionResponse = new ArrayList<Question>();
		
		when(questionPersistence.findByCategory(100L)).thenReturn(questionResponse);
		
		List<Question> questions = questionService.findByCategory(100L);
		assertEquals(0, IterableUtil.sizeOf(questions));
	}
	
	@Test
	@DisplayName("Se borran todas las preguntas")
	void delete_all_question() {
		doNothing().when(questionPersistence).deleteQuestions();
		
		questionService.deleteQuestions();
		
		verify(questionPersistence).deleteQuestions();
	}
	
	@Test
	@DisplayName("Se generan las preguntas")
	void generate_questions() {
		doNothing().when(questionPersistence).generatedQuestions();
		
		questionService.generateQuestions();
		
		verify(questionPersistence).generatedQuestions();
	}

}
