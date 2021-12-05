package not.nerds.secretsantaservice.request;

import java.util.Date;

public class ExchangePostRequest {
    private String name;
    private Date startDate;
    private Date endDate;
    private int host;
    private Iterable<Integer> participants;

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

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public Iterable<Integer> getParticipants() {
        return participants;
    }

    public void setParticipants(Iterable<Integer> participants) {
        this.participants = participants;
    }
}
