package syg.mysql.adapter.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import syg.domain.exception.NotFoundException;
import syg.domain.model.Question;
import syg.domain.model.WikiData;
import syg.mysql.adapter.QuestionAdapter;
import syg.mysql.configuration.UnitAdapterTest;
import syg.mysql.entities.CategoryEntity;
import syg.mysql.entities.QuestionEntity;
import syg.mysql.repositories.AnswerRepository;
import syg.mysql.repositories.CategoryRepository;
import syg.mysql.repositories.QuestionRepository;

@UnitAdapterTest
public class QuestionAdapterTests {
	
	@Autowired
	private QuestionAdapter questionAdapter;
	
	@MockBean
	private QuestionRepository questionRepository;
	
	@MockBean
	private AnswerRepository answerRepository;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@MockBean
	private WikiData wikiData;
	
	@Test
	@DisplayName("Se buscan todas las preguntas en base de datos")
	void find_all_questions() {
		List<QuestionEntity> questionEntitiesResponse = new ArrayList<QuestionEntity>();
		questionEntitiesResponse.add(new QuestionEntity(1L, "Pregunta 1", 60, new CategoryEntity(1L, "animales")));
		questionEntitiesResponse.add(new QuestionEntity(2L, "Pregunta 2", 60, new CategoryEntity(2L, "deportes")));
		questionEntitiesResponse.add(new QuestionEntity(3L, "Pregunta 3", 60, new CategoryEntity(3L, "plantas")));
		
		when(questionRepository.findAll()).thenReturn(questionEntitiesResponse);
		
		List<Question> questions = questionAdapter.findAll();
		assertEquals(3, IterableUtil.sizeOf(questions));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	void find_question_by_id() {
		Optional<QuestionEntity> questionEntityResponse = Optional.of(new QuestionEntity(1L, "Pregunta 1", 60, new CategoryEntity(1L, "animales")));
		
		when(questionRepository.findById(1L)).thenReturn(questionEntityResponse);
		
		Question question = questionAdapter.findById(1L);
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
		
		when(questionRepository.findById(50L)).thenThrow(NotFoundException.class);
		
		exec = () -> questionAdapter.findById(50L);
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria")
	void find_questions_by_category() {
		List<QuestionEntity> questionEntitiesResponse = new ArrayList<QuestionEntity>();
		questionEntitiesResponse.add(new QuestionEntity(1L, "Pregunta 1", 60, new CategoryEntity(1L, "animales")));
		questionEntitiesResponse.add(new QuestionEntity(5L, "Pregunta 5", 60, new CategoryEntity(1L, "animales")));
		
		when(questionRepository.findByCategory_Id(1L)).thenReturn(questionEntitiesResponse);
		
		List<Question> questions = questionAdapter.findByCategory(1L);
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
		List<QuestionEntity> questionEntitiesResponse = new ArrayList<QuestionEntity>();
		
		when(questionRepository.findByCategory_Id(100L)).thenReturn(questionEntitiesResponse);
		
		List<Question> questions = questionAdapter.findByCategory(100L);
		assertEquals(0, IterableUtil.sizeOf(questions));
	}
	
	@Test
	@DisplayName("Se borran todas las preguntas")
	void delete_all_question() {
		doNothing().when(questionRepository).deleteAll();
		
		questionAdapter.deleteQuestions();
		
		verify(questionRepository).deleteAll();
	}
	
	@Test
	@DisplayName("Se generan todas las preguntas para la aplicación")
	void generated_all_question() {
		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
		categories.add(new CategoryEntity(1L, "Deportes"));
		categories.add(new CategoryEntity(2L, "Paises"));
		categories.add(new CategoryEntity(3L, "Ciencia"));
		categories.add(new CategoryEntity(4L, "Cine"));
		categories.add(new CategoryEntity(5L, "Videojuegos"));
		List<QuestionEntity> questionsGenerated = new ArrayList<QuestionEntity>();
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 1", 10, categories.get(0)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 2", 10, categories.get(0)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 3", 10, categories.get(1)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 4", 10, categories.get(0)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 5", 10, categories.get(1)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 6", 10, categories.get(2)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 7", 10, categories.get(2)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 8", 10, categories.get(3)));
		questionsGenerated.add(new QuestionEntity(1L, "Pregunta 9", 10, categories.get(4)));
		
		when(categoryRepository.findAll()).thenReturn(categories);
		when(questionRepository.saveAll(questionsGenerated)).thenReturn(questionsGenerated);
		
		questionAdapter.generatedQuestions();
		verify(categoryRepository, times(1)).findAll();
		verify(questionRepository, times(5)).saveAll(any());
	}
}