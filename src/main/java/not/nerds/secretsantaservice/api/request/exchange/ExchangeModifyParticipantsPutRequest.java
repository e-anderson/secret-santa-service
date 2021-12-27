package not.nerds.secretsantaservice.api.request.exchange;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

public class ExchangeModifyParticipantsPutRequest {
    @NotNull(message="Participants is a required element.")
    @NotEmpty(message="Participants must contain at least one ID.")
    private List<Integer> participants;

    public List<Integer> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Integer> participants) {
        this.participants = participants;
    }
}
