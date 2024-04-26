package syg.bootstrap.e2e;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import syg.bootstrap.SYGdbContainer;
import syg.bootstrap.configuration.BootstrapRunner;
import syg.bootstrap.configuration.E2ETests;
import syg.domain.model.User;

@E2ETests
public class UserTests extends SYGdbContainer {
	
	@MockBean
	private BootstrapRunner bootstrapRunner;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Se buscan todos los usuarios")
	@Order(1)
	void find_all_users() throws Exception{
		mockMvc.perform(get("/user")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id en base de datos")
	@Order(2)
	void find_user_by_id() throws Exception{
		mockMvc.perform(get("/user/userId").param("id", "5366fdc8-b32d-46bc-9df8-2e8ce68f0743")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.id", is("5366fdc8-b32d-46bc-9df8-2e8ce68f0743")))
		.andExpect(jsonPath("$.name", is("Pablo")));
	}
	
	@Test
	@DisplayName("Se busca un  usuarios a traves de un id que no existe")
	@Order(3)
	void find_user_by_not_exist_id() throws Exception{
		mockMvc.perform(get("/user/userId").param("id", "9566fdc8-b32d-46bc-9df8-2e8ce68f0743")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	@Order(4)
	void find_user_by_name() throws Exception{
		mockMvc.perform(get("/user/name").param("userName", "Pablo")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.id", is("5366fdc8-b32d-46bc-9df8-2e8ce68f0743")));
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de nombre que no existe")
	@Order(5)
	void find_user_by_not_exist_name() throws Exception{
		mockMvc.perform(get("/user/name").param("userName", "NotExistPlayer")
				.with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario")
	@Order(6)
	void create_user() throws Exception{
		User userToCreate = new User("9366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate))
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is("9366fdc8-b32d-46bc-9df8-2e8ce68f0743")))
			.andExpect(jsonPath("$.name", is("Jugador 3")));
	}
	
	@Test
	@DisplayName("Se crea un usuario con un id ya existente")
	@Order(7)
	void create_user_with_existent_id() throws Exception{
		User userToCreate = new User("9366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 4", 1, 3, 1, 100, "Deportes");
		
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate))
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	@Order(8)
	void create_user_with_existent_name() throws Exception{
		User userToCreate = new User("7366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 1, 3, 1, 100, "Deportes");
		
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToCreate))
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.CONFLICT.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	@Order(9)
	void update_user_() throws Exception{
		User userToUpdate = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 9, 40, 1, 105, "Deportes");
		
		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate))
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.id", is("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")))
			.andExpect(jsonPath("$.totalGames", is(9)));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	@Order(10)
	void update_user_with_not_existent_id() throws Exception{
		User userToUpdate = new User("9166fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 1, 3, 1, 100, "Deportes");
		
		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate))
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	@Order(11)
	void update_user_with_null_id() throws Exception{
		User userToUpdate = new User(null, "Alvaro", 1, 3, 1, 100, "Deportes");
		
		mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userToUpdate))
                .with(SecurityMockMvcRequestPostProcessors.jwt()))
			.andDo(MockMvcResultHandlers.print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
    private String asJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
}
}
