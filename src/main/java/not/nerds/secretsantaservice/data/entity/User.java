package not.nerds.secretsantaservice.data.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="birth_date")
    private Date birthDate;

    @ManyToMany(mappedBy = "participants")
    private List<Exchange> exchangesParticipatedIn;

    @OneToMany(mappedBy = "host")
    private List<Exchange> exchangesHosted;

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
}