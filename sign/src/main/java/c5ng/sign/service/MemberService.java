package c5ng.sign.service;

import c5ng.sign.entity.Member;
import c5ng.sign.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(Member member) {
        memberRepository.save(member);
    }
}
