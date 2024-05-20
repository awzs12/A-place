package example.controller;

import example.domain.Member;
import example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

//스프링 빈을 등록하는 2가지 방법: 컴포넌트 스캔(@Service,Controller,Repository->@Component)과 의존관계 설정, 자바코드로 직접 스프링 빈 등록하기

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";

    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
      Member member = new Member();
      member.setName(form.getName());

      memberService.join(member);

      return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
