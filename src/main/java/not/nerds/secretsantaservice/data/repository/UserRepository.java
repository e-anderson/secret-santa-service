package not.nerds.secretsantaservice.data.repository;

import not.nerds.secretsantaservice.data.entity.Exchange;
import not.nerds.secretsantaservice.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    Optional<User> findById(Integer integer);
}
