package c5ng.sign.controller;

import c5ng.sign.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {

    @GetMapping("/signUp")
    public String member(Model model) {
        log.info("회원가입 페이지 입갤");

        model.addAttribute("member", new Member());
        return "signUp";
    }

    @PostMapping("/signUp")
    public void member2() {
        log.info("회원가입 버튼 클릭");
    }
}
