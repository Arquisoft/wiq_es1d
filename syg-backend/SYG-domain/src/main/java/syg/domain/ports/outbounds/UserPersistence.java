package syg.domain.ports.outbounds;

import syg.domain.model.User;

public interface UserPersistence {

	public User findById(Long id);
	
	public User findByName(String name);
	
	public User createUser(User user);
	
	public User updateUser(User user);
	
}
