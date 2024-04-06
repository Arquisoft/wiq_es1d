package syg.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import syg.domain.model.User;
import syg.domain.ports.inbound.UserService;
import syg.domain.ports.outbounds.UserPersistence;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired 
	private UserPersistence userPersistence;

	@Override
	public List<User> findAll() {
		return userPersistence.findAll();
	}
	
	@Override
	public User findById(String id) {
		return userPersistence.findById(id);
	}
	
	@Override
	public User findByName(String name) {
		return userPersistence.findByName(name);
	}

	@Override
	public User createUser(User user) {
		return userPersistence.createUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userPersistence.updateUser(user);
	}

}
