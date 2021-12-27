package not.nerds.secretsantaservice.api.service;

import not.nerds.secretsantaservice.api.request.UserPutRequest;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.api.request.UserPostRequest;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;


import java.util.Optional;

@Component
public class UserService {
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

    public User modifyUser(int id, UserPutRequest request) throws Exception {
        Optional<User> userResult = this.userRepository.findById(id);
        if(userResult.isPresent()) {
            User user = userResult.get();

            user.setEmail(StringUtils.isEmpty(request.getEmail()) ? user.getEmail() : request.getEmail());
            user.setFirstName(StringUtils.isEmpty(request.getFirstName()) ? user.getFirstName() : request.getFirstName());
            user.setLastName(StringUtils.isEmpty(request.getLastName()) ? user.getLastName() : request.getLastName());
            user.setBirthDate(request.getBirthDate() == null ? user.getBirthDate() : request.getBirthDate());

            return this.userRepository.save(user);
        } else {
            throw new Exception("User with id " + id + " not found.");
        }
    }
}
