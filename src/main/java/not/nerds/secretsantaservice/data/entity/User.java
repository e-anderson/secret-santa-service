package not.nerds.secretsantaservice.data.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="\"user\"")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="birth_date")
    private Date birthDate;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Exchange> exchangesParticipatedIn;

    @OneToMany(mappedBy = "host")
    @JsonIgnore
    private List<Exchange> exchangesHosted;

    @OneToMany(mappedBy="gifter")
    @JsonIgnore
    private List<Match> matchesAsGifter;

    @OneToMany(mappedBy="recipient")
    @JsonIgnore
    private List<Match> matchesAsRecipient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Exchange> getExchangesParticipatedIn() {
        return exchangesParticipatedIn;
    }

    public void setExchangesParticipatedIn(List<Exchange> exchangesParticipatedIn) {
        this.exchangesParticipatedIn = exchangesParticipatedIn;
    }

    public List<Exchange> getExchangesHosted() {
        return exchangesHosted;
    }

    public void setExchangesHosted(List<Exchange> exchangesHosted) {
        this.exchangesHosted = exchangesHosted;
    }

    public List<Match> getMatchesAsGifter() {
        return matchesAsGifter;
    }

    public void setMatchesAsGifter(List<Match> matchesAsGifter) {
        this.matchesAsGifter = matchesAsGifter;
    }

    public List<Match> getMatchesAsRecipient() {
        return matchesAsRecipient;
    }

    public void setMatchesAsRecipient(List<Match> matchesAsRecipient) {
        this.matchesAsRecipient = matchesAsRecipient;
    }
}
