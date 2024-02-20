package syg.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import syg.mysql.entities.AnswerEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

}
