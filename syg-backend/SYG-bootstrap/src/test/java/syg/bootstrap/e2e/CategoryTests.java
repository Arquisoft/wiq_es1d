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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;


import syg.bootstrap.SYGdbContainer;
import syg.bootstrap.configuration.BootstrapRunner;
import syg.bootstrap.configuration.E2ETests;

@E2ETests
public class CategoryTests extends SYGdbContainer {

	@MockBean
	private BootstrapRunner bootstrapRunner;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Se buscan todas las categorias en base de datos")
	@Order(1)
	void find_all_categories() throws Exception{
		mockMvc.perform(get("/category")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()", is(5)));
	}
	
	@Test
	@DisplayName("Se busca una pregunta a traves de un id en base de datos")
	@Order(2)
	void find_question_by_id() throws Exception{
		mockMvc.perform(get("/category/id").param("id", "1")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("animales")));
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id que no existe")
	@Order(3)
	void find_category_by_not_exist_id() throws Exception{
		mockMvc.perform(get("/category/id").param("id", "100")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
}
