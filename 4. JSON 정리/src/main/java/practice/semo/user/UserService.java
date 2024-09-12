package practice.semo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void userAdd(String username, String password, String email){
        UserTable2 table = new UserTable2();
        table.setUsername(username);
        table.setPassword(password);
        table.setEmail(email);
        userRepository.save(table);
    }



}
