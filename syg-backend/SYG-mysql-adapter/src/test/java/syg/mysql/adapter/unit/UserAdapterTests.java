package syg.mysql.adapter.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
import syg.domain.model.User;
import syg.mysql.adapter.UserAdapter;
import syg.mysql.configuration.UnitAdapterTest;
import syg.mysql.entities.UserEntity;
import syg.mysql.repositories.UserRepository;

@UnitAdapterTest
public class UserAdapterTests {

	@Autowired
	private UserAdapter userAdapter;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	@DisplayName("Se busca un usuario a traves de un id en base de datos")
	void find_user_by_id() {
		Optional<UserEntity> userEntityToReturn = Optional.of(new UserEntity("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 8, 24, 12, 100, "Deportes"));
		
		when(userRepository.findById("4366fdc8-b32d-46bc-9df8-2e8ce68f0743")).thenReturn(userEntityToReturn);
		
		User user = userAdapter.findById("4366fdc8-b32d-46bc-9df8-2e8ce68f0743");
		verify(userRepository, times(1)).findById("4366fdc8-b32d-46bc-9df8-2e8ce68f0743");
		assertEquals("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Pablo", user.getName());
		assertEquals(8, user.getTotalGames());
		assertEquals(24, user.getCorrectAnswers());
		assertEquals(12, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un  usuarios a traves de un id que no existe")
	void find_user_by_not_exist_id() {
		final Executable exec;
		
		when(userRepository.findById(50L)).thenThrow(NotFoundException.class);
		
		exec = () -> userAdapter.findById("5566fdc8-b32d-46bc-9df8-2e8ce68f0743");
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de su nombre")
	void find_user_by_name() {
		Optional<UserEntity> userEntityToReturn = Optional.of(new UserEntity("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Pablo", 8, 24, 12, 100, "Deportes"));
		
		when(userRepository.findByName("Pablo")).thenReturn(userEntityToReturn);
		
		User user = userAdapter.findByName("Pablo");
		verify(userRepository, times(1)).findByName("Pablo");
		assertEquals("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Pablo", user.getName());
		assertEquals(8, user.getTotalGames());
		assertEquals(24, user.getCorrectAnswers());
		assertEquals(12, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se busca un usuario a traves de nombre que no existe")
	void find_user_by_not_exist_name() {
		final Executable exec;
		
		when(userRepository.findByName("NotExistPlayer")).thenThrow(NotFoundException.class);
		
		exec = () -> userAdapter.findByName("NotExistPlayer");
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario")
	void create_user() {
		UserEntity userEntityToReturn = new UserEntity("6666fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		UserEntity userToCreate = new UserEntity(null, "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		when(userRepository.findById(userToCreate.getId())).thenReturn(Optional.empty());
		when(userRepository.save(userToCreate)).thenReturn(userEntityToReturn);
		
		User user = userAdapter.createUser(new User(null, "Jugador 3", 1, 3, 1, 100, "Deportes"));
		verify(userRepository, times(1)).save(userToCreate);
		assertEquals("6666fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Jugador 3", user.getName());
		assertEquals(1, user.getTotalGames());
		assertEquals(3, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se crea un usuario con un id ya existente")
	void create_user_with_existent_id() {
		final Executable exec;
		UserEntity userToCreate = new UserEntity("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes");
		
		when(userRepository.findById(userToCreate.getId())).thenReturn(Optional.of(userToCreate));
		when(userRepository.save(userToCreate)).thenThrow(ConflictException.class);

		exec = () -> userAdapter.createUser(new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Jugador 3", 1, 3, 1, 100, "Deportes"));
		verify(userRepository, never()).save(userToCreate);
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se crea un usuario con un nombre ya existente")
	void create_user_with_existent_name() {
		final Executable exec;
		UserEntity userToCreate = new UserEntity(null, "Alvaro", 1, 3, 1, 100, "Deportes");
		
		when(userRepository.save(userToCreate)).thenThrow(ConflictException.class);

		exec = () -> userAdapter.createUser(new User(null, "Alvaro", 1, 3, 1, 100, "Deportes"));
		verify(userRepository, never()).save(userToCreate);
		assertThrows(ConflictException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario")
	void update_user_() {
		UserEntity userEntityToReturn = new UserEntity("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 4, 40, 1, 100, "Deportes");
		UserEntity userToUpdate = new UserEntity("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 4, 40, 1, 100, "Deportes");
		
		when(userRepository.findById(userToUpdate.getId())).thenReturn(Optional.of(userToUpdate));
		when(userRepository.save(userToUpdate)).thenReturn(userEntityToReturn);
		
		User user = userAdapter.updateUser(new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 4, 40, 1, 100, "Deportes"));
		verify(userRepository, times(1)).save(userToUpdate);
		assertEquals("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", user.getId());
		assertEquals("Alvaro", user.getName());
		assertEquals(4, user.getTotalGames());
		assertEquals(40, user.getCorrectAnswers());
		assertEquals(1, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id que no existe")
	void update_user_with_not_existent_id() {
		final Executable exec;
		UserEntity userToUpdate = new UserEntity("7766fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 1, 3, 1, 100, "Deportes");
		
		when(userRepository.findById(userToUpdate.getId())).thenReturn(Optional.empty());
		when(userRepository.save(userToUpdate)).thenThrow(NotFoundException.class);

		exec = () -> userAdapter.updateUser(new User("4366fdc8-b32d-46bc-9df8-2e8ce68f0743", "Alvaro", 1, 3, 1, 100, "Deportes"));
		verify(userRepository, never()).save(userToUpdate);
		assertThrows(NotFoundException.class, exec);
	}
	
	@Test
	@DisplayName("Se actualiza un usuario con un id nulo")
	void update_user_with_null_id() {
		final Executable exec;
		UserEntity userToUpdate = new UserEntity(null, "Alvaro", 1, 3, 1, 100, "Deportes");
		
		when(userRepository.save(userToUpdate)).thenThrow(NotFoundException.class);

		exec = () -> userAdapter.updateUser(new User(null, "Alvaro", 1, 3, 1, 100, "Deportes"));
		verify(userRepository, never()).save(userToUpdate);
		assertThrows(NotFoundException.class, exec);
	}
}
