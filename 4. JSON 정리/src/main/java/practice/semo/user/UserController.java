package practice.semo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    // 모든 유저 정보를 JSON 형태로 반환
    @GetMapping
    public List<UserTable2> getUsers() {
        return userRepository.findAll();
    }

    // 유저 등록 요청 처리
    @PostMapping("/register")
    public void registerUser(@RequestBody List<UserDTO> users) {
        for (UserDTO userDTO : users) {
            userService.userAdd(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        }
    }
}