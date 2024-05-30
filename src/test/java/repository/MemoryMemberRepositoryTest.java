package repository;

import example.domain.Member;
import example.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    void save() {
        // given
        Member member = new Member();
        member.setName("spring");
        member.setEmail("spring@example.com");

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Optional<Member> result = memberRepository.findById(savedMember.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedMember);
    }

    @Test
    @DisplayName("이메일로 회원 찾기 테스트")
    void findByEmail() {
        // given
        Member member = new Member();
        member.setName("spring");
        member.setEmail("spring@example.com");
        memberRepository.save(member);

        // when
        Optional<Member> result = memberRepository.findByEmail("spring@example.com");

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("spring@example.com");
    }

    @Test
    @DisplayName("이름으로 회원 찾기 테스트")
    void findByName() {
        // given
        Member member = new Member();
        member.setName("spring");
        member.setEmail("spring@example.com");
        memberRepository.save(member);

        // when
        Optional<Member> result = memberRepository.findByName("spring");

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("spring");
    }

    @Test
    @DisplayName("모든 회원 찾기 테스트")
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        member1.setEmail("spring1@example.com");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        member2.setEmail("spring2@example.com");
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
