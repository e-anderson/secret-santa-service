package not.nerds.secretsantaservice.api.contoller;

import not.nerds.secretsantaservice.api.request.exchange.ExchangeModifyParticipantsPutRequest;
import not.nerds.secretsantaservice.api.response.GetResponse;
import not.nerds.secretsantaservice.api.response.PostResponse;
import not.nerds.secretsantaservice.api.response.PutResponse;
import not.nerds.secretsantaservice.service.ExchangeService;
import not.nerds.secretsantaservice.data.entity.Exchange;
import not.nerds.secretsantaservice.api.request.exchange.CreateExchangePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @PostMapping
    public PostResponse<Exchange> createExchange(@RequestBody CreateExchangePostRequest request) throws Exception {
            return new PostResponse<>(this.exchangeService.createExchange(request), HttpStatus.OK);
    }

    @GetMapping
    public GetResponse<Iterable<Exchange>> getAllExchanges() {
        return new GetResponse<>(this.exchangeService.getAllExchanges(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public GetResponse<Exchange> getExchangeById(@PathVariable(value="id") int id)  {
            return new GetResponse<>(this.exchangeService.getExhangeById(id), HttpStatus.OK);
    }

    @GetMapping("/host/{hostId}")
    public GetResponse<Iterable<Exchange>> getExchangesByHostId(@PathVariable(value="hostId") int hostId)  {
        return new GetResponse<>(this.exchangeService.getExchangesByHostId(hostId), HttpStatus.OK);
    }

//    TODO move to user controller
//    @GetMapping("/participant/{participantId}")
//    public GetResponse<Iterable<Exchange>> getExchangesByParticipantId(@PathVariable(value="participantId") int participantId) {
//        return new GetResponse<>(this.exchangeService.findByParticipantId(participantId), HttpStatus.OK);
//    }

    @PutMapping("/{id}/participant")
    public PutResponse<Exchange> addParticipantsToExchange(@PathVariable(value="id") int exchangeId,
                                                          @Valid @RequestBody ExchangeModifyParticipantsPutRequest request) throws Exception {
        return new PutResponse<>(this.exchangeService.addParticipantsToExchange(exchangeId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/participant")
    public PutResponse<Exchange> removeParticipantsFromExchange(@PathVariable(value="id") int exchangeId,
                                                           @Valid @RequestBody ExchangeModifyParticipantsPutRequest request) throws Exception {
        return new PutResponse<>(this.exchangeService.removeParticipantsFromExchange(exchangeId, request), HttpStatus.OK);
    }
}
