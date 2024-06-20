package example.repository;

import example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
public interface MemoryMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);


    Optional<Member> findByName(String name);

}