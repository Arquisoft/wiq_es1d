package services.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import configuration.UnitDomainTest;
import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
import syg.domain.model.User;
import syg.domain.ports.inbound.UserService;
import syg.domain.ports.outbounds.UserPersistence;

@UnitDomainTest
public class UserServiceTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserPersistence userPersistence;
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id")
	void find_user_by_id() {
		User UserToReturn = new User(2L, "Pablo", "player", 8, 24, 12);
		
		when(userPersistence.findById(2L)).thenReturn(UserToReturn);
		
		User user = userService.findById(2L);
		verify(userPersistence, times(1)).findById(2L);
		assertEquals(2L, user.getId());
		assertEquals("Pablo", user.getName());
		assertEquals(8, user.getTotalGames());
		assertEquals(24, user.getCorrectAnswers());
		assertEquals(12, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un  usuarios a traves de un id que no existe")
	void find_user_by_not_exist_id() {
		final Executable exec;
		
		when(userPersistence.findById(50L)).thenThrow(NotFoundException.class);
		
		exec = () -> userService.findById(50L);
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	void find_user_by_name() {
		User UserToReturn = new User(2L, "Pablo", "player", 8, 24, 12);
		
		when(userPersistence.findByName("Pablo")).thenReturn(UserToReturn);
		
		User user = userService.findByName("Pablo");
		verify(userPersistence, times(1)).findByName("Pablo");
		assertEquals(2L, user.getId());
		assertEquals("Pablo", user.getName());
		assertEquals(8, user.getTotalGames());
		assertEquals(24, user.getCorrectAnswers());
		assertEquals(12, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de nombre que no existe")
	void find_user_by_not_exist_name() {
		final Executable exec;
		
		when(userPersistence.findByName("NotExistPlayer")).thenThrow(NotFoundException.class);
		
		exec = () -> userService.findByName("NotExistPlayer");
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario")
	void create_user() {
		User UserToReturn = new User(4L, "Jugador 3", "player", 1, 3, 1);
		User userToCreate = new User(null, "Jugador 3", "player", 1, 3, 1);
		
		when(userPersistence.createUser(userToCreate)).thenReturn(UserToReturn);
		
		User user = userService.createUser(new User(null, "Jugador 3", "player", 1, 3, 1));
		verify(userPersistence, times(1)).createUser(userToCreate);
		assertEquals(4L, user.getId());
		assertEquals("Jugador 3", user.getName());
		assertEquals(1, user.getTotalGames());
		assertEquals(3, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se crea un usuario con un id ya existente")
	void create_user_with_existent_id() {
		final Executable exec;
		User userToCreate = new User(1L, "Jugador 3", "player", 1, 3, 1);
		
		when(userPersistence.createUser(userToCreate)).thenThrow(ConflictException.class);

		exec = () -> userService.createUser(new User(1L, "Jugador 3", "player", 1, 3, 1));
		verify(userPersistence, never()).createUser(userToCreate);
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	void create_user_with_existent_name() {
		final Executable exec;
		User userToCreate = new User(null, "Alvaro", "player", 1, 3, 1);
		
		when(userPersistence.createUser(userToCreate)).thenThrow(ConflictException.class);

		exec = () -> userService.createUser(new User(null, "Alvaro", "player", 1, 3, 1));
		verify(userPersistence, never()).createUser(userToCreate);
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	void update_user_() {
		User UserToReturn = new User(1L, "Alvaro", "adminActualizado", 4, 40, 1);
		User userToUpdate = new User(1L, "Alvaro", "adminActualizado", 4, 40, 1);
		
		when(userPersistence.updateUser(userToUpdate)).thenReturn(UserToReturn);
		
		User user = userService.updateUser(new User(1L, "Alvaro", "adminActualizado", 4, 40, 1));
		verify(userPersistence, times(1)).updateUser(userToUpdate);
		assertEquals(1L, user.getId());
		assertEquals("Alvaro", user.getName());
		assertEquals(4, user.getTotalGames());
		assertEquals(40, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	void update_user_with_not_existent_id() {
		final Executable exec;
		User userToUpdate = new User(10L, "Alvaro", "player", 1, 3, 1);
		
		when(userPersistence.updateUser(userToUpdate)).thenThrow(NotFoundException.class);

		exec = () -> userService.updateUser(new User(10L, "Alvaro", "player", 1, 3, 1));
		verify(userPersistence, never()).updateUser(userToUpdate);
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	void update_user_with_null_id() {
		final Executable exec;
		User userToUpdate = new User(null, "Alvaro", "player", 1, 3, 1);
		
		when(userPersistence.updateUser(userToUpdate)).thenThrow(NotFoundException.class);

		exec = () -> userService.updateUser(new User(null, "Alvaro", "player", 1, 3, 1));
		verify(userPersistence, never()).updateUser(userToUpdate);
		assertThrows(NotFoundException.class, exec);
	}
}
