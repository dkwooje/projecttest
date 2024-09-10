package practice.semo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.userAdd(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<String> userMain() {
        return ResponseEntity.ok().build();
    }

}