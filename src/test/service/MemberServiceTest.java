package service;

import example.domain.Member;
import example.repository.MemoryMemberRepository;
import example.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    //test코드는 실제 코드에 포함x

    MemberService memberservice;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }



    @Test
    public void 회원가입() {
        //given --> 이런게 주어지면 (이 데이터를 기반으로 하는구나)
        Member member = new Member();
        member.setName("hello");

        //when --> 이게 주어졌을 때(이걸 검증하는구나)
        Long saveId =  memberservice.join(member);

        //then --> 이렇게 실행 되어야 돼(여기가 검증부구나)
        Member findMember = memberservice.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1  = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class , () -> memberservice.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//
//            memberservice.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

         //then

        

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}