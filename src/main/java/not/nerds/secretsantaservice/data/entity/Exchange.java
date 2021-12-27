package not.nerds.secretsantaservice.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="exchange")
public class Exchange {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="created_on")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
    private Date createdDate;

    @Column(name="start_date")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
    private Date startDate;

    @Column(name="end_date")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="host_id")
    @JsonManagedReference
    private User host;

    @ManyToMany
    @JoinTable(
            name="exchange_has_participant",
            joinColumns=@JoinColumn(name="exchange_id"),
            inverseJoinColumns = @JoinColumn(name="participant_id")
    )
    @JsonManagedReference
    private List<User> participants;

    @OneToMany(mappedBy = "exchange")
    private List<Match> matches;

    @Column(name="match_date")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
    private Date matchDate;

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
