package not.nerds.secretsantaservice.api.request.user;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserPostRequest {
    @NotNull(message="Email is a required field.")
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    @NotNull(message="ExternalId is a required field.")
    private String externalId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
