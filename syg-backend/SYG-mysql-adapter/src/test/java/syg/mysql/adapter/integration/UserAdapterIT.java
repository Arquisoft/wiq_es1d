package syg.mysql.adapter.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
import syg.domain.model.User;
import syg.mysql.SYGdbContainerIT;
import syg.mysql.adapter.UserAdapter;
import syg.mysql.configuration.IntegrationAdapterTest;

@IntegrationAdapterTest
public class UserAdapterIT extends SYGdbContainerIT {

	@Autowired
	private UserAdapter userAdapter;
	
	@Test
	@DisplayName("Se buscan todos los usuarios")
	void find_all_users() {
		
		List<User> users = userAdapter.findAll();
		
		assertEquals(2, users.size());
		assertEquals(2, users.size());
		assertEquals("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", users.get(0).getId());
		assertEquals("Pablo", users.get(1).getName());

	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id en base de datos")
	void find_user_by_id() {
		User user = userAdapter.findById("4366fdc8-b32d-46bc-9df8-2e8ce68f0743");
		
		assertEquals("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Alvaro", user.getName());
		assertEquals(4, user.getTotalGames());
		assertEquals(33, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un  usuarios a traves de un id que no existe")
	void find_user_by_not_exist_id() {
		final Executable exec;
		
		exec = () -> userAdapter.findById("2366fdc8-b32d-46bc-9df8-2e8ce68f0743");
		
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	void find_user_by_name() {
		User user = userAdapter.findByName("Pablo");
		
		assertEquals("5366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
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
		User userToCreate = new User("8366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		User user = userAdapter.createUser(userToCreate);
		
		assertEquals("8366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Jugador 3", user.getName());
		assertEquals(1, user.getTotalGames());
		assertEquals(3, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se crea un usuario con un id ya existente")
	void create_user_with_existent_id() {
		final Executable exec;
		User userToCreate = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		exec = () -> userAdapter.createUser(userToCreate);
		
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	void create_user_with_existent_name() {
		final Executable exec;
		User userToCreate = new User("6666fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 1, 3, 1, 100, "Deportes");
		
		exec = () -> userAdapter.createUser(userToCreate);
		
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	void update_user_() {
		User userToUpdate = new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador Actualizado", 10, 40, 1, 100, "Deportes");
		
		User user = userAdapter.updateUser(userToUpdate);
		
		assertEquals("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Jugador Actualizado", user.getName());
		assertEquals(10, user.getTotalGames());
		assertEquals(40, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	void update_user_with_not_existent_id() {
		final Executable exec;
		User userToUpdate = new User("5566fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 1, 3, 1, 100, "Deportes");
		
		exec = () -> userAdapter.updateUser(userToUpdate);
		
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	void update_user_with_null_id() {
		final Executable exec;
		User userToUpdate = new User(null, "Alvaro", 1, 3, 1, 100, "Deportes");
		
		exec = () -> userAdapter.updateUser(userToUpdate);
		
		assertThrows(NotFoundException.class, exec);
	}
}
