package practice.semo.user;


import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/user")
    String user(){
        return "/usermain.html";
    }

    @GetMapping("/user/register")
    String register1(){
        return "/userRegister.html";
    }
    @PostMapping("/user/register")
    String register2(@RequestParam String username,
                     @RequestParam String password,
                     @RequestParam String email){
        userService.userAdd(username, password, email);
        return "/usermain.html";
    }


    @GetMapping("/user/login")
    String login1(){
        return "/userLogin.html";
    }


}
