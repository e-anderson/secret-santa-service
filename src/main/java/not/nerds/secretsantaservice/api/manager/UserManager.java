package not.nerds.secretsantaservice.api.manager;

import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.api.request.UserPostRequest;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    @Autowired
    private UserRepository userRepository;

    public User createNewUser(UserPostRequest request) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setBirthDate(request.getBirthDate());
            user.setExternalId(request.getExternalId());
            userRepository.save(user);
            return user;
    }

    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(int id) {
        return this.userRepository.findById(id).get();
    }
}
