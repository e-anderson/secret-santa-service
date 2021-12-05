package not.nerds.secretsantaservice.contoller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import not.nerds.secretsantaservice.data.entity.Exchange;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.ExchangeRepository;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import not.nerds.secretsantaservice.request.ExchangePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public boolean createExchange(@RequestBody ExchangePostRequest request) {
        try {
            Optional<User> host = userRepository.findById(request.getHost());
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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/{id}")
    public Exchange getExchangeById(@PathVariable int id)  {
        return this.exchangeRepository.findById(id).get();
    }

    @GetMapping("/host/{hostId}")
    public List<Exchange> getExchangesByHostId(@PathVariable int hostId) {
        return this.exchangeRepository.findByHost_Id(hostId).stream().toList();
    }

    @GetMapping("/participant/{participantId}")
    public List<Exchange> getExchangesByParticipantId(@PathVariable int participantId) throws JsonProcessingException {
        return this.exchangeRepository.findByParticipants_Id(participantId).stream().toList();
    }

    @GetMapping
    public Iterable<Exchange> getExchanges() {
        return this.exchangeRepository.findAll();
    }
}
