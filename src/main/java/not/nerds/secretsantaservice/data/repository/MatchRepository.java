package not.nerds.secretsantaservice.data.repository;

import not.nerds.secretsantaservice.data.entity.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Integer> {
}
