package syg.mysql.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import syg.domain.model.User;
import syg.domain.ports.outbounds.UserPersistence;
import syg.mysql.entities.UserEntity;
import syg.mysql.mapper.UserMapper;
import syg.mysql.repositories.UserRepository;

@Component
public class UserAdapter implements UserPersistence {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUser(Long id) {
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		if(optionalUserEntity.isEmpty()) {
			throw new NullPointerException();
		}
		return userMapper.toDomain(optionalUserEntity.get());
	}

	@Override
	public User createUser(User user) {
		UserEntity userEntity = userRepository.save(userMapper.toEntity(user));
		return userMapper.toDomain(userEntity);
	}

	@Override
	public User updateUser(User user) {
		UserEntity userEntity = userRepository.save(userMapper.toEntity(user));
		return userMapper.toDomain(userEntity);
	}

}
