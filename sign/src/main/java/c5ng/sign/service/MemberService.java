package c5ng.sign.service;

import c5ng.sign.entity.Member;
import c5ng.sign.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void addMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword())); /* 비밀번호 암호화 */

        memberRepository.save(member);
    }

    public Optional<Member> login(Member member) {
        return memberRepository.findByEmail(member.getEmail())
                .filter(findMember -> passwordEncoder.matches(member.getPassword(), findMember.getPassword()));
    }

    /* filter 메서드를 사용하여 비밀번호 검증 조건을 확인한다. 비밀번호가 일치하는 경우에 Optional<Member> (findMember) 반환.
    * 비밀번호가 일치하지 않는 경우에는 Optional.empty()가 반환된다.*/
}
