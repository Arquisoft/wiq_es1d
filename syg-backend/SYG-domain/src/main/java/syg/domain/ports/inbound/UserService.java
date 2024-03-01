package syg.domain.ports.inbound;

import syg.domain.model.User;

public interface UserService {

	public User findById(Long id);
	
	public User createUser(User user);
	
	public User updateUser(User user);
}
