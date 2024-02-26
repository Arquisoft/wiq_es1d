package syg.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import syg.mysql.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
