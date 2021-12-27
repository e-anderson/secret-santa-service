package not.nerds.secretsantaservice.api.service;

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
public class ExchangeService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    public Exchange createExchange(ExchangePostRequest request) throws Exception {
        Optional<User> host = userRepository.findById(request.getHost());
        if (host.isEmpty()) {
            throw new Exception("Host user ID " + request.getHost() + " not found.");
        } else {
            Exchange exchange = new Exchange();
            exchange.setHost(host.get());
            exchange.setCreatedDate(new Date());
            exchange.setStartDate(request.getStartDate());
            exchange.setEndDate(request.getEndDate());
            exchange.setName(request.getName());

            //get participant users from IDs
            List<User> participants = StreamSupport.stream(userRepository.findAllById(request.getParticipants()).spliterator(), false).toList();
            exchange.setParticipants(participants);
            this.exchangeRepository.save(exchange);
            return exchange;
        }
    }

    public Exchange getExhangeById(int id) {
        return this.exchangeRepository.findById(id).get();
    }

    public Iterable<Exchange> getExchangesByHostId(int hostId) {
        return this.exchangeRepository.findByHost_Id(hostId);
    }

    public Iterable<Exchange> findByParticipantId(int participantId) {
        return this.exchangeRepository.findByParticipants_Id(participantId);
    }

    public Iterable<Exchange> getAllExchanges() {
        return this.exchangeRepository.findAll();
    }
}
