package syg.domain.ports.outbounds;

import syg.domain.model.User;

public interface UserPersistence {

	public User findUser(Long id);
	
	public User createUser(User user);
	
	public User updateUser(User user);
	
}
