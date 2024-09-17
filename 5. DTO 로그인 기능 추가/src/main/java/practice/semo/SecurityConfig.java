package practice.semo;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository()) //csrf기능 키기
                .ignoringRequestMatchers("/user/login")  //해당 페이지에서 csrf기능 끄기
        );
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );

        http.formLogin((formLogin) -> formLogin.loginPage("/user/login")//로그인 페이지 url
                        .defaultSuccessUrl("/user/success") //로그인 성공시 들어가는 url
                //  .failureUrl("/") //실패시 이돟하는 url
        );
        // http.logout(logout -> logout.logoutUrl("/logout"));

        return http.build();
    }

}

// http.csrf((csrf) -> csrf.disable()); csrf보안 끄기
//permitAll: 항상 허용
//FilterChain:유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드 담는 곳
//SecurityFilterChain 특정페이지 검사할지 설정 가능