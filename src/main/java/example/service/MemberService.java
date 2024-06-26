package example.service;

import example.entity.Member;
import example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("memberService")
@Transactional
public class MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member create(String username, String name, String email, String password, String phoneNumber) {
        Member user = new Member();
        user.setUserid(username);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        this.memberRepository.save(user);
        return user;
    }

    public Member registerMember(Member member) {
        validateDuplicateMemberByEmail(member); // 중복 회원 검증
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    public Optional<Member> login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(member -> passwordEncoder.matches(password, member.getPassword()));


    }

    private void validateDuplicateMemberByEmail(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /*전체 회원 조회*/
    public List<Member> findMembers(){
      return  memberRepository.findAll();
    }
    public Optional<Member>findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public void join(Member member2) {
    }
}
