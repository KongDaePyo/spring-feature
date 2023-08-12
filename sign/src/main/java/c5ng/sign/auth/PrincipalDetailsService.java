package c5ng.sign.auth;

import c5ng.sign.entity.Member;
import c5ng.sign.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/* Security 설정에서 loginProcessingUrl("/login")을 해놨으니 /login Request가 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행된다. */

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /* 파라미터로 있는 username은 request가 들어올 때 username으로 들어와야 한다. 즉 서로 변수가 같아야한다. (SecurityConfig에서 바꿀 수 있다.) */
    /* Security Session 안에 Autentication 타입이 들어가고 그 안에 UserDetails 타입이 들어간다. */
    /* Security Session 내부 (Autentication 내부 (UserDetails)) */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);

        return new PrincipalDetails(member);
    }
}
