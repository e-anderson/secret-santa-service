package not.nerds.secretsantaservice.data.dto;

import not.nerds.secretsantaservice.data.entity.Exchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExchangeDto {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private ExchangeHostDto host;
    private List<ExchangeParticipantDto> participants;

    public ExchangeDto(Exchange exchange) {
        this.id=exchange.getId();
        this.name= exchange.getName();
        this.startDate = exchange.getStartDate();
        this.endDate = exchange.getEndDate();
        this.host = new ExchangeHostDto(exchange.getHost());
        this.participants = new ArrayList<>();
        exchange.getParticipants().forEach(p -> {
            this.participants.add(new ExchangeParticipantDto(p));
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ExchangeHostDto getHost() {
        return host;
    }

    public void setHost(ExchangeHostDto host) {
        this.host = host;
    }

    public List<ExchangeParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ExchangeParticipantDto> participants) {
        this.participants = participants;
    }
}
