package syg.domain.services;

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
	public User findById(Long id) {
		return userPersistence.findById(id);
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
