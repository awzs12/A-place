package example.controller;

import example.entity.Member;
import example.dto.MemberForm;
import example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

//스프링 빈을 등록하는 2가지 방법: 컴포넌트 스캔(@Service,Controller,Repository->@Component)과 의존관계 설정, 자바코드로 직접 스프링 빈 등록하기

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/login")
    public String login() {
        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Optional<Member> member = memberService.login(email, password);

        if (member.isPresent()) {
            session.setAttribute("member", member.get());
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "members/login";
        }
    }

    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/members/login/createMemberForm")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }


    @PostMapping("/members/login/createMemberForm")
    public String createMember(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }
        if (!memberForm.getPassword1().equals(memberForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword1());
        member.setEmail(memberForm.getEmail());
        member.setPhoneNumber(memberForm.getPhoneNumber());

        memberService.registerMember(member);

        return "redirect:/members/login"; // 회원 생성 후 로그인 페이지로 이동
    }
}
