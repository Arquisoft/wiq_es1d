package syg.mysql.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import syg.mysql.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findById(String id);

	Optional<UserEntity> findByName(String name);

}
