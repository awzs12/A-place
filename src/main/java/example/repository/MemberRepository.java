package example.repository;

import entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    void clearStore();

    Optional<Member> findByName(String spring);

    Optional<Member> findByEmail(String s);
}