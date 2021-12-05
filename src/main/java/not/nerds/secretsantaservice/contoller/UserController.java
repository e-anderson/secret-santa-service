package not.nerds.secretsantaservice.contoller;

import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.UserRepository;
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
}
