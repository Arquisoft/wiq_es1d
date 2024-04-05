package syg.mysql.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import syg.domain.exception.ConflictException;
import syg.domain.exception.NotFoundException;
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
	public User findById(String id) {
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		if(optionalUserEntity.isEmpty()) {
			throw new NotFoundException("The user with id " + id + " does not exist");
		}
		return userMapper.toDomain(optionalUserEntity.get());
	}
	
	@Override
	public User findByName(String name) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByName(name);
		if(optionalUserEntity.isEmpty()) {
			throw new NotFoundException("The user with name " + name + " does not exist");
		}
		return userMapper.toDomain(optionalUserEntity.get());
	}

	@Override
	public User createUser(User user) {
		if(user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
			throw new ConflictException("The user with id " + user.getId() + " alredy exist");
		}
		try {			
			UserEntity userEntity = userRepository.save(userMapper.toEntity(user));
			return userMapper.toDomain(userEntity);
		} catch (DataIntegrityViolationException e) {
			throw new ConflictException("The user with name " + user.getName() + " alredy exist");
		}
	}

	@Override
	public User updateUser(User user) {
		if(user.getId() == null || userRepository.findById(user.getId()).isEmpty()) {
			throw new NotFoundException("The user with id " + user.getId() + " not exist");
		}
		UserEntity userEntity = userRepository.save(userMapper.toEntity(user));
		return userMapper.toDomain(userEntity);
	}

}
