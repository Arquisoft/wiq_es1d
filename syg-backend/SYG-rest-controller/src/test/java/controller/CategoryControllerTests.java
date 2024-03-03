package controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import configuration.ControllerTest;
import syg.domain.exception.NotFoundException;
import syg.domain.model.Category;
import syg.domain.ports.inbound.CategoryService;
import syg.domain.ports.inbound.QuestionService;
import syg.domain.ports.inbound.UserService;

@ControllerTest(CategoryControllerTests.class)
public class CategoryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;
	
	@MockBean
	private QuestionService questionService;
	
	@MockBean
	private UserService userService;
	
	@Test
	@DisplayName("Se buscan todas las categorias")
	void find_all_categories() throws Exception {
		List<Category> categoriesExpected = new ArrayList<>();
		categoriesExpected.add(new Category(1L, "Categoria 1"));
		categoriesExpected.add(new Category(2L, "Categoria 2"));
		categoriesExpected.add(new Category(3L, "Categoria 2"));
		
		when(categoryService.findAll()).thenReturn(categoriesExpected);

		mockMvc.perform(get("/category"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id ")
	void find_category_by_id() throws Exception {
		Category categoryExpected = new Category(1L, "Categoria 1");

		when(categoryService.findById(1L)).thenReturn(categoryExpected);
		
		mockMvc.perform(get("/category/id").param("id", "1"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	@DisplayName("Se busca una categoria a traves de un id que no existe")
	void find_category_by_not_exist_id() throws Exception {
		when(categoryService.findById(100L)).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/category/id").param("id", "100"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
}
