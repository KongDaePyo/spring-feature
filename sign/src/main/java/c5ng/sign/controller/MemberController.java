package c5ng.sign.controller;

import c5ng.sign.entity.Member;
import c5ng.sign.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("여기 탐 ?");

        return "login";
    }

    @PostMapping("/login")
    public String login(Member member) {
        Optional<Member> loginMember = memberService.login(member);

        if (loginMember.isPresent()) {
            log.info("email : " + loginMember.get().getEmail());
            log.info("password : " + loginMember.get().getPassword());
        } else {
            log.info("값 존재 X");
        }

        return "redirect:/";
    }
}
