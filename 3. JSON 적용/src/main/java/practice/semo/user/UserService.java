package practice.semo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void userAdd(UserDTO userDTO) {
        UserTable table = new UserTable();
        table.setUsername(userDTO.getUsername());
        table.setPassword(userDTO.getPassword());
        table.setEmail(userDTO.getEmail());
        userRepository.save(table);
    }
}