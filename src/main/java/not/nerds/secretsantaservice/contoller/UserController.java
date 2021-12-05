package not.nerds.secretsantaservice.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import not.nerds.secretsantaservice.data.dto.UserDto;
import not.nerds.secretsantaservice.data.entity.User;
import not.nerds.secretsantaservice.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUsers() throws JsonProcessingException {
        Iterable<User> users = this.userRepository.findAll();
        List<UserDto> results = new ArrayList<UserDto>();

        users.forEach(u -> {
            UserDto result = new UserDto(u);
            results.add(result);
        });

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( results );
        return json;
    }
}
