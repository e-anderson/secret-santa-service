package not.nerds.secretsantaservice.api.manager;

import not.nerds.secretsantaservice.data.entity.Exchange;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.ExchangeRepository;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import not.nerds.secretsantaservice.api.request.ExchangePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class ExchangeManager {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    public Exchange createExchange(ExchangePostRequest request) throws Exception {
        Optional<User> host = userRepository.findById(request.getHost());
        try {
            if(host.isEmpty()) {
                throw new Exception("Host user ID " + request.getHost() + " not found.");
            } else {
                Exchange exchange = new Exchange();
                exchange.setHost(host.get());
                exchange.setCreatedDate(new Date());
                exchange.setStartDate(request.getStartDate());
                exchange.setEndDate(request.getEndDate());
                exchange.setName(request.getName());

                //get participant users from IDs
                List<User> participants = StreamSupport.stream(userRepository.findAllById(request.getParticipants()).spliterator(),false).toList();
                exchange.setParticipants(participants);
                this.exchangeRepository.save(exchange);
                return exchange;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
