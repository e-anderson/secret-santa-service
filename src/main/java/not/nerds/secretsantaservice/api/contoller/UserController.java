package not.nerds.secretsantaservice.api.contoller;

import not.nerds.secretsantaservice.api.request.UserPutRequest;
import not.nerds.secretsantaservice.api.response.GetResponse;
import not.nerds.secretsantaservice.api.response.PostResponse;
import not.nerds.secretsantaservice.api.response.PutResponse;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.api.request.UserPostRequest;
import not.nerds.secretsantaservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    /**
     * Returns list of all users without limits.
     *
     * @return List of all users.
     */
    @GetMapping
    public GetResponse<Iterable<User>> getUsers() {
        return new GetResponse<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Returns a single user.
     *
     * @param id The ID of the requested user.
     * @return The user object.
     */
    @GetMapping("/{id}")
    public GetResponse<User> getUserById(@PathVariable(value="id") int id) {
            return new GetResponse<>(this.userService.getUserById(id), HttpStatus.OK);
    }

    /**
     * Creates a new user. Must include the user's email address and an external user ID from the auth provider.
     *
     * @param request The user information with multiple optional fields and two required fields.
     * @return The newly created user object.
     */
    @PostMapping
    public PostResponse<User> createUser(@Valid @RequestBody UserPostRequest request) {
            User user = userService.createNewUser(request);
            return new PostResponse<>(user, HttpStatus.OK);
    }

    /**
     * Modifies an existing user.
     *
     * @param id The id of the user to be modified.
     * @param request The user information with multiple optional fields.
     * @return The newly created user object.
     */
    @PutMapping("/{id}")
    public PutResponse<User> createUser(@PathVariable int id, @RequestBody UserPutRequest request) throws Exception {
        User user = userService.modifyUser(id, request);
        return new PutResponse<>(user, HttpStatus.OK);
    }
}
