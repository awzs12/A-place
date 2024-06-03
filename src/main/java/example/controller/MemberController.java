package example.controller;

import example.domain.Member;
import example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

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

    @GetMapping("/members/login/createMemberForm")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }


    @PostMapping("/members/login/createMemberForm")
    public String createMember(@ModelAttribute MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        member.setPhoneNumber(form.getPhoneNumber());

        memberService.registerMember(member);

        return "redirect:/members/login"; // 회원 생성 후 로그인 페이지로 이동
    }
}



//      memberService.join(member);
//      System.out.println(member.getName() + member.getId() + member.getEmail() + member.getPhoneNumber());
//
//      return "redirect:/members/login";
//    }

//    @GetMapping("/members")
//    public String list(Model model){
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }
