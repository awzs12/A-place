package example.repository;

import example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Member")
    void clearStore();

    Optional<Member> findByName(String spring);

    Optional<Member> findByEmail(String s);
}