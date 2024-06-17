package service;

import entity.Member;
import example.repository.MemberRepository;
import example.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void registerMember() {
        // given
        Member member = new Member();
        member.setName("hello");
        member.setEmail("hello@example.com");

        given(memberRepository.findByEmail(member.getEmail())).willReturn(Optional.empty());
        given(memberRepository.save(any(Member.class))).willReturn(member);

        // when
        Member registeredMember = memberService.registerMember(member);

        // then
        assertThat(registeredMember).isEqualTo(member);
        verify(memberRepository).save(member);
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        given(memberRepository.findByName(member1.getName())).willReturn(Optional.of(member1));

        Member member2 = new Member();
        member2.setName("spring");

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.registerMember(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    // 추가적인 테스트 케이스들을 구현할 수 있습니다.

}
