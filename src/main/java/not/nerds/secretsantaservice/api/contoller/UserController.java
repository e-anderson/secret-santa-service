package not.nerds.secretsantaservice.api.contoller;

import not.nerds.secretsantaservice.api.response.GetResponse;
import not.nerds.secretsantaservice.api.response.PostResponse;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.api.request.UserPostRequest;
import not.nerds.secretsantaservice.api.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserManager userManager;

    public UserController() {
    }

    @GetMapping
    public GetResponse<Iterable<User>> getUsers() {
        try {
            return new GetResponse<Iterable<User>>(this.userManager.getAllUsers(), HttpStatus.OK);
        } catch (Exception ex) {
            return new GetResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public GetResponse<User> getUserById(@PathVariable int id) {
        try {
            return new GetResponse<User>(this.userManager.getUserById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new GetResponse<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public PostResponse<User> createUser(@Valid @RequestBody UserPostRequest request) {
        try {
            User user = userManager.createNewUser(request);
            return new PostResponse<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new PostResponse<>(HttpStatus.BAD_REQUEST);
        }
    }
}
