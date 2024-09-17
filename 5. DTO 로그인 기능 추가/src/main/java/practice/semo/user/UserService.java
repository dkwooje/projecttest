package practice.semo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void userAdd(UserDTO userDTO){
        UserTable table = new UserTable();
        var hash = passwordEncoder.encode(userDTO.getPassword());
        table.setUsername(userDTO.getUsername());
        table.setPassword(hash);
        table.setEmail(userDTO.getEmail());
        userRepository.save(table);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB에서 username을 가진 유저를 찾아와서
        // return new User(유저아이디, 비번, 권한) 해주세요
        var result = userRepository.findByUsername(username); //username과 일치하는 행을 가져옴
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("그런 아이디 없음");
        }
        var user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반유저")); //나중에 API에서 현재 유저의 정보 출력 가능
        return new User(user.getUsername(), user.getPassword(), authorities);


    }

}
