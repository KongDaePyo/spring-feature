package c5ng.sign.controller;

import c5ng.sign.entity.Member;
import c5ng.sign.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signUp")
    public String member(Model model) {
        model.addAttribute("member", new Member());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String member(Member member) {
        memberService.addMember(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginMember", new Member());

        return "login";
    }

//    @PostMapping("/login")
//    public String login(Member member) {
//        Optional<Member> loginMember = memberService.login(member);
//
//        if (loginMember.isPresent()) {
//            log.info("email : " + loginMember.get().getEmail());
//            log.info("password : " + loginMember.get().getPassword());
//        } else {
//            log.info("값 존재 X");
//        }
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//
//        return "redirect:/";
//    } 직접 로그인 로직을 구성하는 경우 아직 개발 X

    @GetMapping("/session")
    public String session() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            log.info("Pricipal : " + authentication.getPrincipal());
            log.info("Credentials : " + authentication.getCredentials());
            log.info("Authorities : " + authentication.getAuthorities());
            log.info("Details : " + authentication.getDetails());

            log.info("현재 로그인 된 email : " + email);
        }


        return "session";
    }
}
