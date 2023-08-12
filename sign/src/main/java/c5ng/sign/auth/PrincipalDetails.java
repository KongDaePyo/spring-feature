package c5ng.sign.auth;

import c5ng.sign.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/* Security가 /login URI 요청이 오면 로그인 진행시킨다. 로그인 진행 완료가 되면 Security Session을 만들어준다. Security ContextHolder라는 key값을 담아 Session을 저장 */
/* Security Session에 들어갈 수 있는 Object는 Authentication 타입 객체로 정해져있다. */
/* Authentication 안에는 User 정보가 있어야 한다.  */
/* User에 들어갈 수 있는 Object는 UserDetails 타입 객체로 정해져있다. */
/* Security Session -> Authentication Object -> UserDetails Object */

public class PrincipalDetails implements UserDetails {

    private Optional<Member> member;

    public PrincipalDetails(Optional<Member> member) {
        this.member = member;
    }

    /* 해당 User의 권한을 리턴하는 메서드 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();

        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.get().getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return member.get().getPassword();
    }

    @Override
    public String getUsername() {
        return member.get().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
