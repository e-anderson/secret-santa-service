package not.nerds.secretsantaservice.request;

public class CreateUserPostRequest {
    private String email;
    private String auth0_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth0_id() {
        return auth0_id;
    }

    public void setAuth0_id(String auth0_id) {
        this.auth0_id = auth0_id;
    }
}
