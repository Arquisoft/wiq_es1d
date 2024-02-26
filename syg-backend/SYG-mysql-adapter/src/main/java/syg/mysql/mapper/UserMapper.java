package syg.mysql.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import syg.domain.model.User;
import syg.mysql.entities.UserEntity;

@Component
public class UserMapper {
	
	public UserEntity toEntity(User user) {
		return new UserEntity(user.getId(),user.getName(), user.getPassword(), user.getTotalGames(), 
				user.getCorrectAnswers(), user.getInCorrectAnswers());
	}
	
	public List<UserEntity> toEntity(List<User> users) {
		List<UserEntity> usersEntity = new ArrayList<UserEntity>();
		for (User user : users) {
			usersEntity.add(toEntity(user));
		}
		return usersEntity;
	}
	
	public User toDomain(UserEntity userEntity) {
		return new User(userEntity.getId(),userEntity.getName(), userEntity.getPassword(), userEntity.getTotalGames(), 
				userEntity.getCorrectAnswers(), userEntity.getInCorrectAnswers());
	}
	
	public List<User> toDomain(List<UserEntity> usersEntity) {
		List<User> users = new ArrayList<User>();
		for (UserEntity userEntity : usersEntity) {
			users.add(toDomain(userEntity));
		}
		return users;
	}

}
