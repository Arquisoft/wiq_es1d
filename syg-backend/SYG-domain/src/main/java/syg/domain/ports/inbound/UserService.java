package syg.domain.ports.inbound;

import syg.domain.model.User;

public interface UserService {

	public User findUser(Long id);
	
	public User createUser(User user);
	
	public User updateUser(User user);
}
