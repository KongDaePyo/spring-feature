package c5ng.sign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity /* security 활성화 */
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/member/**").authenticated()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/LoginProc")
//                .defaultSuccessUrl("/")
                .and().build();
    }
    /*
        http.csrf().disable() == CSRF 공격에 대한 방어를 해제한다.
        authorizeRequests() == URI에 따른 페이지에 대한 권한을 부여하기 위해 시작하는 메서드. antMatchers 기능을 이용하기 위해 사용
        .antMatchers() == 특정 URL 접근 시 인가가 필요한 URI를 설정할 수 있다. .authenticated()를 사용하면 해당 URI는 인증이 필요한 URI라고 명시 가능
        .anyRequest().permitAll() == 특정 URI를 제외한 나머지 URI는 전부 인가해준다.
        .formLogin() == 아이디와 비밀번호를 입력해서 들어오는 로그인 형태를 지원하는 security 기능. 아래와 같은 loginPage() 메서드 사용 가능
        .loginPage("/login") == 로그인 페이지를 설정한다. 로그인 하지 않았을 경우 로그인이 필요한 페이지를 들어갔을 때 로그인 페이지로 다시 redirect 하기 위한 메서드. 즉 인가되지 않은 사용자에게 보여줄 페이지를 설정한다.

     */
}
