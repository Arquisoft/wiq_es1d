package controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
	@DisplayName("Se buscan todos los usuarios")
	void find_all_users() throws Exception {
		List<User> usersExpected = new ArrayList<User>();
		usersExpected.add(new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 8, 24, 12, 100, "Deportes"));
		usersExpected.add(new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0753", "aAlaro", 8, 4, 2, 100, "Animales"));
		usersExpected.add(new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0763", "Juan", 3, 2, 1, 100, "Deportes"));
		
		when(userService.findAll()).thenReturn(usersExpected);
		
		mockMvc.perform(get("/user"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	@DisplayName("Se buscan todos los usuarios pero ninguno existe")
	void find_all_users_but_no_one_exist() throws Exception {
		List<User> usersExpected = new ArrayList<User>();
		
		when(userService.findAll()).thenReturn(usersExpected);
		
		mockMvc.perform(get("/user"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.length()", is(0)));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id")
	void find_user_by_id() throws Exception {
		User userToReturn = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 8, 24, 12, 100, "Deportes");
		
		when(userService.findById("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")).thenReturn(userToReturn);
		
		mockMvc.perform(get("/user/userId").param("id", "4366fdc8-b32d-46bc-9df8-2e8ce68f0743"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id que no existe")
	void find_user_by_not_exist_id() throws Exception {
		when(userService.findById("5566fdc8-b32d-46bc-9df8-2e8ce68f0743")).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/user/userId").param("id", "5566fdc8-b32d-46bc-9df8-2e8ce68f0743"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id y algo va mal")
	void find_user_by_id_but_something_goes_wrong() throws Exception {
		when(userService.findById("5566fdc8-b32d-46bc-9df8-2e8ce68f0743")).thenThrow(new RuntimeException("Service is unavailable"));
		
		mockMvc.perform(get("/user/userId").param("id", "5566fdc8-b32d-46bc-9df8-2e8ce68f0743"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	void find_user_by_name() throws Exception {
		User UserToReturn = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 8, 24, 12, 100, "Deportes");
		
		when(userService.findByName("Pablo")).thenReturn(UserToReturn);
		
		mockMvc.perform(get("/user/name").param("userName", "Pablo"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un nombre que no existe")
	void find_user_by_not_exist_name() throws Exception {
		
		when(userService.findByName("NotExistPlayer")).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/user/name").param("userName", "NotExistPlayer"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un nombre y algo va mal")
	void find_user_by_name_but_something_goes_wrong() throws Exception {
		when(userService.findByName("ServiceUnavailablePlayer")).thenThrow(new RuntimeException("Service is unavailable"));
		
		mockMvc.perform(get("/user/name").param("userName", "ServiceUnavailablePlayer"))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario")
	void create_user() throws Exception {
		User UserToReturn = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		User userToCreate = new User(null, "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		when(userService.createUser(userToCreate)).thenReturn(UserToReturn);
		
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")));
	}
	
	@Test
	@DisplayName("Se crea un usuario con un id ya existente")
	void create_user_with_existent_id() throws Exception {
		User userToCreate = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		when(userService.createUser(userToCreate)).thenThrow(ConflictException.class);

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	void create_user_with_existent_name() throws Exception {
		User userToCreate = new User(null, "Alvaro", 1, 3, 1, 100, "Deportes");
		
		when(userService.createUser(userToCreate)).thenThrow(ConflictException.class);

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario pero algo va mal")
	void create_user_but_something_goes_wrong() throws Exception {
		User userToCreate = new User(null, "Jugador 30", 1, 3, 1, 100, "Deportes");
		
		when(userService.createUser(userToCreate)).thenThrow(new RuntimeException("Service is unavailable"));

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate)))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	void update_user_() throws Exception {
		User UserToReturn = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 4, 40, 1, 100, "Deportes");
		User userToUpdate = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 4, 40, 1, 100, "Deportes");
		
		when(userService.updateUser(userToUpdate)).thenReturn(UserToReturn);
		
		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	void update_user_with_not_existent_id() throws Exception {
		User userToUpdate = new User("1166fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 1, 3, 1, 100, "Deportes");
		
		when(userService.updateUser(userToUpdate)).thenThrow(NotFoundException.class);

		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	void update_user_with_null_id() throws Exception {
		User userToUpdate = new User(null, "Alvaro", 1, 3, 1, 100, "Deportes");
		
		when(userService.updateUser(userToUpdate)).thenThrow(NotFoundException.class);

		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un nombre que ya existe")
	void update_user_with_exist_name() throws Exception {
		User userToUpdate = new User("1166fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 1, 3, 1, 100, "Deportes");
		
		when(userService.updateUser(userToUpdate)).thenThrow(ConflictException.class);

		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario pero algo va mal")
	void update_user_but_something_goes_wrong() throws Exception {
		User userToUpdate = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		when(userService.updateUser(userToUpdate)).thenThrow(new RuntimeException("Service is unavailable"));

		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate)))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()));
	}
	
    private String asJsonString(Object obj) throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(obj);
    }
}
