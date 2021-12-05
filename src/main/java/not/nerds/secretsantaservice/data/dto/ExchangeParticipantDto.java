package not.nerds.secretsantaservice.data.dto;

import not.nerds.secretsantaservice.data.entity.User;

import java.util.Date;

public class ExchangeParticipantDto {
    private int id;
    private String email;
    private Date birthDate;

    public ExchangeParticipantDto(User user) {
        this.id=user.getId();
        this.email=user.getEmail();
        this.birthDate=user.getBirthDate();
    }

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
}
