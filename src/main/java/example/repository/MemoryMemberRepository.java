package example.repository;

import example.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
public interface MemoryMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

}