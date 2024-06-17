package service;

import entity.Member;
import example.repository.MemberRepository;
import example.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest  //통합 테스트 용도
@Transactional //테스트 끝나면 롤백해줌
class MemberServiceIntegraionTest {
    //test코드는 실제 코드에 포함x

    @Autowired  MemberService memberservice;
    @Autowired  MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given --> 이런게 주어지면 (이 데이터를 기반으로 하는구나)
        Member member = new Member();
        member.setName("spring");
        member.setEmail("spring@example.com");
        member.setPassword("password123");


        //when --> 이게 주어졌을 때(이걸 검증하는구나)
        Long saveId = memberservice.registerMember(member).getId();

        //then --> 이렇게 실행 되어야 돼(여기가 검증부구나)
        Member findMember = memberservice.findOne(saveId).get();
        assertEquals(member.getEmail(), findMember.getEmail());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1  = new Member();
        member1.setName("spring");
        member1.setEmail("spring");
        member1.setPassword("spring");
        member1.setPhoneNumber("spring");



        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberservice.registerMember(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class , () -> memberservice.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



    }

}