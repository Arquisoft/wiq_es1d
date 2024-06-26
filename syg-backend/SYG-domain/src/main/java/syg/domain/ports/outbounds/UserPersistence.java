package syg.domain.ports.outbounds;

import java.util.List;

import syg.domain.model.User;

public interface UserPersistence {

	/**
	 * Método para encontrar a todos los usuarios.
	 * 
	 * @return List<User> La lista con todos los usuarios
	 */
	public List<User> findAll();
	
	/**
	 * Método para encontrar a un usuario por su identificador unico.
	 * 
	 * @param id Identificador del usuario
	 * @return User
	 */
	public User findById(String id);

	/**
	 * Método para encontrar a un usuario por su nombre de usuario unico.
	 * 
	 * @param name Nombre de usuario
	 * @return User
	 */
	public User findByName(String name);

	/**
	 * Método para crear un nuevo usuario.
	 * 
	 * @param user El nuevo usuario
	 * @return User
	 */
	public User createUser(User user);

	/**
	 * Método para actualizar la información de un usuario.
	 * 
	 * @param user El usuario a actualizar
	 * @return User
	 */
	public User updateUser(User user);

}
