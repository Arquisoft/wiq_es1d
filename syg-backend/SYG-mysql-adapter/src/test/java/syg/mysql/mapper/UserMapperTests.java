package syg.mysql.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import syg.domain.model.User;
import syg.mysql.configuration.UnitAdapterTest;
import syg.mysql.entities.UserEntity;

@UnitAdapterTest
public class UserMapperTests {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	@DisplayName("Se mapea un objeto user de dominio a entidad")
	void model_to_entity() {
		UserEntity userEntity = userMapper.toEntity(new User(1L, "User 1", "Password 1", 60, 40 ,10));
		
		assertEquals(1L, userEntity.getId());
		assertEquals("User 1", userEntity.getName());
		assertEquals("Password 1", userEntity.getPassword());
		assertEquals(60, userEntity.getTotalGames());
		assertEquals(40, userEntity.getCorrectAnswers());
		assertEquals(10, userEntity.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos users de dominio a entidad")
	void model_to_entity_list() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1L, "User 1", "Password 1", 60, 40 ,10));
		users.add(new User(2L, "User 2", "Password 2", 70, 30 ,30));
		users.add(new User(3L, "User 3", "Password 3", 80, 10 ,50));
		
		List<UserEntity> usersEntity = userMapper.toEntity(users);
		
		assertEquals(3, usersEntity.size());
		assertEquals(1L, usersEntity.get(0).getId());
		assertEquals("User 1", usersEntity.get(0).getName());
		assertEquals("Password 1", usersEntity.get(0).getPassword());
		assertEquals(60, usersEntity.get(0).getTotalGames());
		assertEquals(40, usersEntity.get(0).getCorrectAnswers());
		assertEquals(10, usersEntity.get(0).getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se mapea un objeto user de entidad a dominio")
	void entity_to_model() {
		User user = userMapper.toDomain(new UserEntity(1L, "User 1", "Password 1", 60, 40 ,10));
		
		assertEquals(1L, user.getId());
		assertEquals("User 1", user.getName());
		assertEquals("Password 1", user.getPassword());
		assertEquals(60, user.getTotalGames());
		assertEquals(40, user.getCorrectAnswers());
		assertEquals(10, user.getInCorrectAnswers());
	}
	
	@Test
	@DisplayName("Se mapea una lista de objetos users de entidad a dominio")
	void entity_to_model_list() {
		List<UserEntity> userEntity = new ArrayList<UserEntity>();
		userEntity.add(new UserEntity(1L, "User 1", "Password 1", 60, 40 ,10));
		userEntity.add(new UserEntity(2L, "User 2", "Password 2", 70, 30 ,30));
		userEntity.add(new UserEntity(3L, "User 3", "Password 3", 80, 10 ,50));
		
		List<User> users = userMapper.toDomain(userEntity);
		
		assertEquals(3, users.size());
		assertEquals(1L, users.get(0).getId());
		assertEquals("User 1", users.get(0).getName());
		assertEquals("Password 1", users.get(0).getPassword());
		assertEquals(60, users.get(0).getTotalGames());
		assertEquals(40, users.get(0).getCorrectAnswers());
		assertEquals(10, users.get(0).getInCorrectAnswers());
	}
}
