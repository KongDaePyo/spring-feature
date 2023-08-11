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
        memberService.createMember(member);

        return "redirect:/";
    }
}
