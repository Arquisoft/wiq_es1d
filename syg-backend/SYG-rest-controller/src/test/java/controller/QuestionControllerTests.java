package controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import configuration.ControllerTest;
import syg.controller.QuestionsController;
import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.domain.model.Question;
import syg.domain.ports.inbound.CategoryService;
import syg.domain.ports.inbound.QuestionService;
import syg.domain.ports.inbound.UserService;

@ControllerTest(QuestionsController.class)
public class QuestionControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;
	
	@MockBean
	private QuestionService questionService;
	
	@MockBean
	private UserService userService;
	
	@Test
	@DisplayName("Se buscan todas las preguntas en base de datos")
	void find_all_questions() throws Exception {
		List<Question> questionResponse = new ArrayList<Question>();
		questionResponse.add(new Question(1L, "Pregunta 1", 60, new Category(1L, "animales"), new ArrayList<>()));
		questionResponse.add(new Question(2L, "Pregunta 2", 60, new Category(2L, "deportes"), new ArrayList<>()));
		questionResponse.add(new Question(3L, "Pregunta 3", 60, new Category(3L, "plantas"), new ArrayList<>()));
		
		when(questionService.findAll()).thenReturn(questionResponse);
		
		mockMvc.perform(get("/question"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	@DisplayName("Se buscan todas las preguntas en base de datos y no hay ninguna")
	void find_all_questions_but_no_one_exists() throws Exception {
		List<Question> questionResponse = new ArrayList<Question>();
		
		when(questionService.findAll()).thenReturn(questionResponse);
		
		mockMvc.perform(get("/question"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.length()", is(0)));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	void find_question_by_id() throws Exception {
		Question questionResponse = new Question(1L, "Pregunta 1", 60, new Category(1L, "animales"), new ArrayList<>());
		
		when(questionService.findById(1L)).thenReturn(questionResponse);
		
		mockMvc.perform(get("/question/id").param("id", "1"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id que no existe")
	void find_question_by_not_exist_id() throws Exception {
		when(questionService.findById(50L)).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/question/id").param("id", "50"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id y algo va mal")
	void find_question_by_id_and_something_goes_wrong() throws Exception {
		when(questionService.findById(1L)).thenThrow(new RuntimeException("Service is unavailable"));
		
		mockMvc.perform(get("/question/id").param("id", "1"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria")
	void find_questions_by_category() throws Exception {
		List<Question> questionResponse = new ArrayList<Question>();
		questionResponse.add(new Question(1L, "Pregunta 1", 60, new Category(1L, "animales"), new ArrayList<>()));
		questionResponse.add(new Question(5L, "Pregunta 5", 60, new Category(1L, "animales"), new ArrayList<>()));
		
		when(questionService.findByCategory(1L)).thenReturn(questionResponse);
		
		mockMvc.perform(get("/question/category").param("categoryId", "1"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria que no existe")
	void find_question_by_not_exist_category() throws Exception {
		List<Question> questionResponse = new ArrayList<Question>();
		
		when(questionService.findByCategory(100L)).thenReturn(questionResponse);
		
		mockMvc.perform(get("/question/category").param("categoryId", "100"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.length()", is(0)));
	}
}
