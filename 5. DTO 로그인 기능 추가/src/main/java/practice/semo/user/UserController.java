package practice.semo.user;


import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setEmail(email);
        userService.userAdd(userDTO);
        return "/usermain.html";
    }


    @GetMapping("/user/login")
    String login1(String username){
        var result = userRepository.findByUsername(username);
        return "/userLogin.html";
    }

    @GetMapping("/user/success")
    public String myPage(Authentication auth) {
        System.out.println(auth);
        System.out.println(auth.getName());
        System.out.println(auth.isAuthenticated());

        return "userSuccess.html";
    }

}
