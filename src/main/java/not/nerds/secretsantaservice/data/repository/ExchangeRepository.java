package not.nerds.secretsantaservice.data.repository;

import not.nerds.secretsantaservice.data.entity.Exchange;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExchangeRepository extends CrudRepository<Exchange, Integer> {
    List<Exchange> findByHost_Id(int id);
}
