package practice.semo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void userAdd(String username,String password, String email){
        UserTable table = new UserTable();
        table.setUsername(username);
        table.setPassword(password);
        table.setEmail(email);
        userRepository.save(table);
    }



}
