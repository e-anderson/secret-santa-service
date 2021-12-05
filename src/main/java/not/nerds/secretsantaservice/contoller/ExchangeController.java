package not.nerds.secretsantaservice.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import not.nerds.secretsantaservice.data.dto.ExchangeDto;
import not.nerds.secretsantaservice.data.dto.UserDto;
import not.nerds.secretsantaservice.data.entity.Exchange;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.ExchangeRepository;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import not.nerds.secretsantaservice.request.ExchangePostRequest;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                this.exchangeRepository.save(exchange);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/{id}")
    public String getExchangeById(@PathVariable int id) throws JsonProcessingException {
        try {
            Optional<Exchange> exchange = this.exchangeRepository.findById(id);
            if(exchange.isEmpty()) {
                throw new Exception("Exchange not found, ID given was " + id);
            } else {
                return exchangeToDtoJson(exchange.get());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/host/{hostId}")
    public String getExchangesByHostId(@PathVariable int hostId) throws JsonProcessingException {
        Iterable<Exchange> exchanges = this.exchangeRepository.findByHost_Id(hostId);
        return exchangesToDtosJson(exchanges);
    }

    @GetMapping
    public String getExchanges() throws JsonProcessingException {
        Iterable<Exchange> exchanges = this.exchangeRepository.findAll();
        return exchangesToDtosJson(exchanges);
    }

    private Iterable<ExchangeDto> exchangesToDtos(Iterable<Exchange> exchanges) {
        return exchangesToDtos(exchanges);
    }

    private String toJson(Object object) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( object );
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String exchangesToDtosJson(Iterable<Exchange> exchanges) throws JsonProcessingException {
        try {
            return toJson(exchangesToDtos(exchanges));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String exchangeToDtoJson(Exchange exchange) throws JsonProcessingException {
        try {
            return toJson(new ExchangeDto(exchange));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
