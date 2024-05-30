package example.service;

import example.domain.Member;
import example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member registerMember(Member member) {
        validateDuplicateMemberByEmail(member); // 중복 회원 검증
        return memberRepository.save(member);
    }

    private void validateDuplicateMemberByEmail(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
    /*회원 가입*/
    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 x
        validateDuplicateMemberByName(member);  //중복 회원 검증
        //optional로 한번 감싸면 optional안에 member객체가 있는거
        //옛날에는 ifnull 지금은 optional로 감싸기
        //optional로 바로 반환하는거는 추천x
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMemberByName(Member member) {
        memberRepository.findByName(member.getName())//ctrl + alt + v
        .ifPresent(m ->{ //result가 null이 아니면 즉, 값이 있으면 동작
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
}
