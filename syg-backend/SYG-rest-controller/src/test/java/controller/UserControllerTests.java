package controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import configuration.ControllerTest;
import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
import syg.domain.model.User;
import syg.domain.ports.inbound.CategoryService;
import syg.domain.ports.inbound.QuestionService;
import syg.domain.ports.inbound.UserService;

@ControllerTest(UserControllerTests.class)
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;
	
	@MockBean
	private QuestionService questionService;
	
	@MockBean
	private UserService userService;
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id")
	void find_user_by_id() throws Exception {
		User userToReturn = new User(2L, "Pablo", "player", 8, 24, 12);
		
		when(userService.findById(2L)).thenReturn(userToReturn);
		
		mockMvc.perform(get("/user").param("id", "2"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is(2)));
	}
	
	@Test
	@DisplayName("Se busca un  usuarios a traves de un id que no existe")
	void find_user_by_not_exist_id() throws Exception {
		when(userService.findById(50L)).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/user").param("id", "50"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	void find_user_by_name() throws Exception {
		User UserToReturn = new User(2L, "Pablo", "player", 8, 24, 12);
		
		when(userService.findByName("Pablo")).thenReturn(UserToReturn);
		
		mockMvc.perform(get("/user/name").param("userName", "Pablo"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is(2)));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de nombre que no existe")
	void find_user_by_not_exist_name() throws Exception {
		
		when(userService.findByName("NotExistPlayer")).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/user/name").param("userName", "NotExistPlayer"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario")
	void create_user() throws Exception {
		User UserToReturn = new User(4L, "Jugador 3", "player", 1, 3, 1);
		User userToCreate = new User(null, "Jugador 3", "player", 1, 3, 1);
		
		when(userService.createUser(userToCreate)).thenReturn(UserToReturn);
		
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is(4)));
	}
	
	@Test
	@DisplayName("Se crea un usuario con un id ya existente")
	void create_user_with_existent_id() throws Exception {
		User userToCreate = new User(1L, "Jugador 3", "player", 1, 3, 1);
		
		when(userService.createUser(userToCreate)).thenThrow(ConflictException.class);

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	void create_user_with_existent_name() throws Exception {
		User userToCreate = new User(null, "Alvaro", "player", 1, 3, 1);
		
		when(userService.createUser(userToCreate)).thenThrow(ConflictException.class);

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	void update_user_() throws Exception {
		User UserToReturn = new User(1L, "Alvaro", "adminActualizado", 4, 40, 1);
		User userToUpdate = new User(1L, "Alvaro", "adminActualizado", 4, 40, 1);
		
		when(userService.updateUser(userToUpdate)).thenReturn(UserToReturn);
		
		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	void update_user_with_not_existent_id() throws Exception {
		User userToUpdate = new User(100L, "Alvaro", "player", 1, 3, 1);
		
		when(userService.updateUser(userToUpdate)).thenThrow(NotFoundException.class);

		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	void update_user_with_null_id() throws Exception {
		User userToUpdate = new User(null, "Alvaro", "player", 1, 3, 1);
		
		when(userService.updateUser(userToUpdate)).thenThrow(NotFoundException.class);

		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
    public String asJsonString(Object obj) throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(obj);
    }
}
