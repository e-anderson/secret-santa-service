package not.nerds.secretsantaservice.service;

import not.nerds.secretsantaservice.api.request.exchange.ExchangeModifyParticipantsPutRequest;
import not.nerds.secretsantaservice.data.entity.Exchange;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.ExchangeRepository;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import not.nerds.secretsantaservice.api.request.exchange.CreateExchangePostRequest;
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

    public Exchange createExchange(CreateExchangePostRequest request) throws Exception {
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

    public Exchange addOrRemoveParticipantsFromExchange(int exchangeId, ExchangeModifyParticipantsPutRequest request, boolean isAddParticipantRequest) throws Exception {
        Optional<Exchange> exchangeOptional = this.exchangeRepository.findById(exchangeId);
        if (exchangeOptional.isEmpty()) {
            throw new Exception("Exchange ID " + exchangeId + " not found.");
        } else {
            Exchange exchange = exchangeOptional.get();
            List<User> exchangeParticipants = exchange.getParticipants();
            List<Integer> requestedParticipants = request.getParticipants();
            for (int participantId : requestedParticipants) {
                Optional<User> participantOptional = this.userRepository.findById(participantId);
                if (participantOptional.isEmpty()) {
                    throw new Exception("Participant user ID " + participantId + " not found.");
                } else {
                    User user = participantOptional.get();
                    if (isAddParticipantRequest) {
                        // add the user if it isn't already in the list of participants
                        // if it is, that's ok, don't add again
                        if (!exchangeParticipants.contains(user)) {
                            exchangeParticipants.add(user);
                        }
                    } else {
                        // delete the participant if they're in the list
                        // if they aren't in the list, that's ok, don't do anything
                        exchangeParticipants.remove(user);
                    }
                }
            }
            exchange.setParticipants(exchangeParticipants);
            return this.exchangeRepository.save(exchange);
        }
    }

    public Exchange getExhangeById(int id) {
        return this.exchangeRepository.findById(id).get();
    }

    public Iterable<Exchange> getExchangesByHostId(int hostId) {
        return this.exchangeRepository.findByHost_Id(hostId);
    }

    // TODO move to user service
    public Iterable<Exchange> findByParticipantId(int participantId) {
        return this.exchangeRepository.findByParticipants_Id(participantId);
    }

    public Iterable<Exchange> getAllExchanges() {
        return this.exchangeRepository.findAll();
    }
}
