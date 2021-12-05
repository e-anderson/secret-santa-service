package not.nerds.secretsantaservice.contoller;

import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import not.nerds.secretsantaservice.request.CreateUserPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return this.userRepository.findById(id).get();
    }

    @PostMapping
    public boolean createUser(@RequestBody CreateUserPostRequest request) {
        try {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setAuth0Id(request.getAuth0Id());
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
