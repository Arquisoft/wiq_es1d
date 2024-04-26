package syg.bootstrap.e2e;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import syg.bootstrap.SYGdbContainer;
import syg.bootstrap.configuration.BootstrapRunner;
import syg.bootstrap.configuration.E2ETests;

@E2ETests
public class QuestionsTests extends SYGdbContainer {
	
	//Evita que el runner se lance en cada testContainer
	@MockBean
	private BootstrapRunner bootstrapRunner;
	 
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Se buscan todas las preguntas en base de datos")
	@Order(1)
	void find_all_questions() throws Exception {
		mockMvc.perform(get("/question")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()", is(8)));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	@Order(2)
	void find_question_by_id() throws Exception {
		mockMvc.perform(get("/question/id").param("id", "1")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.text", is("¿Cual es el animal que no puede saltar?")));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id que no existe")
	@Order(3)
	void find_question_by_not_exist_id() throws Exception {
		mockMvc.perform(get("/question/id").param("id", "10000")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria")
	@Order(4)
	void find_questions_by_category() throws Exception{
		mockMvc.perform(get("/question/category").param("categoryId", "1")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	@DisplayName("Se buscan preguntas a traves de una categoria que no existe")
	@Order(5)
	void find_question_by_not_exist_category() throws Exception{
		mockMvc.perform(get("/question/category").param("categoryId", "100")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()", is(0)));
	}
}
