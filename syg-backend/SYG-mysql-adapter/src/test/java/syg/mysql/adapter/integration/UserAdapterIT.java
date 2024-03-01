package syg.mysql.adapter.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
import syg.domain.model.User;
import syg.mysql.SYGdbContainerIT;
import syg.mysql.adapter.UserAdapter;
import syg.mysql.configuration.IntegrationTest;

@IntegrationTest
public class UserAdapterIT extends SYGdbContainerIT {

	@Autowired
	private UserAdapter userAdapter;
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id en base de datos")
	void find_user_by_id() {
		User user = userAdapter.findById(2L);
		
		assertEquals(2, user.getId());
		assertEquals("Pablo", user.getName());
		assertEquals(8, user.getTotalGames());
		assertEquals(24, user.getCorrectAnswers());
		assertEquals(12, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un  usuarios a traves de un id que no existe")
	void find_user_by_not_exist_id() {
		final Executable exec;
		
		exec = () -> userAdapter.findById(50L);
		
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	void find_user_by_name() {
		User user = userAdapter.findByName("Pablo");
		
		assertEquals(2, user.getId());
		assertEquals("Pablo", user.getName());
		assertEquals(8, user.getTotalGames());
		assertEquals(24, user.getCorrectAnswers());
		assertEquals(12, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de nombre que no existe")
	void find_user_by_not_exist_name() {
		final Executable exec;
		
		exec = () -> userAdapter.findByName("NotExistPlayerName");
		
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario")
	void create_user() {
		User userToCreate = new User(null, "Jugador 3", "player", 1, 3, 1);
		
		User user = userAdapter.createUser(userToCreate);
		
		assertEquals(4, user.getId());
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
		
		exec = () -> userAdapter.createUser(userToCreate);
		
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	void create_user_with_existent_name() {
		final Executable exec;
		User userToCreate = new User(4L, "Alvaro", "player", 1, 3, 1);
		
		exec = () -> userAdapter.createUser(userToCreate);
		
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	void update_user_() {
		User userToUpdate = new User(1L, "Jugador Actualizado", "player", 10, 40, 1);
		
		User user = userAdapter.updateUser(userToUpdate);
		
		assertEquals(1, user.getId());
		assertEquals("Jugador Actualizado", user.getName());
		assertEquals(10, user.getTotalGames());
		assertEquals(40, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	void update_user_with_not_existent_id() {
		final Executable exec;
		User userToUpdate = new User(40L, "Alvaro", "player", 1, 3, 1);
		
		exec = () -> userAdapter.updateUser(userToUpdate);
		
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	void update_user_with_null_id() {
		final Executable exec;
		User userToUpdate = new User(null, "Alvaro", "player", 1, 3, 1);
		
		exec = () -> userAdapter.updateUser(userToUpdate);
		
		assertThrows(NotFoundException.class, exec);
	}
}
