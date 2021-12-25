package not.nerds.secretsantaservice.api.contoller;

import not.nerds.secretsantaservice.api.response.GetResponse;
import not.nerds.secretsantaservice.api.response.PostResponse;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.api.request.UserPostRequest;
import not.nerds.secretsantaservice.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userManager;

    public UserController() {
    }

    /**
     * Returns list of all users without limits.
     *
     * @return List of all users.
     */
    @GetMapping
    public GetResponse<Iterable<User>> getUsers() {
        try {
            return new GetResponse<>(this.userManager.getAllUsers(), HttpStatus.OK);
        } catch (Exception ex) {
            return new GetResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Returns a single user.
     *
     * @param id The ID of the requested user.
     * @return The user object.
     */
    @GetMapping("/{id}")
    public GetResponse<User> getUserById(@RequestParam(value="id") int id) {
        try {
            return new GetResponse<>(this.userManager.getUserById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new GetResponse<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creates a new user. Must include the user's email address and an external user ID from the auth provider.
     *
     * @param request The user information with multiple optinal fields and two required fields.
     * @return The newly created user object.
     */
    @PostMapping
    public PostResponse<User> createUser(@Valid @RequestBody UserPostRequest request) {
        try {
            User user = userManager.createNewUser(request);
            return new PostResponse<>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new PostResponse<>(HttpStatus.BAD_REQUEST);
        }
    }
}
