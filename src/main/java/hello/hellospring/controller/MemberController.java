package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 다른 컨트롤러에서 MemberService를 가져다가 쓸수 있다.
    // 그래서 하나를 생성해서 여러번 받아와서 쓰면된다.
    // 스프링 컨트롤러에 하나를 등록해서 받아서 사용하자
    private final MemberService memberService;

    // 연결은 생성자로
    // memberSerivce를 스프링 컨트롤러가 생성할때 만들어진다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm() {
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
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
