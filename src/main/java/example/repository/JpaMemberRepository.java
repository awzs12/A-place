package example.repository;

import example.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import example.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByName(String name);

}


//    @Override
//    public Member save(Member member) {
//        em.persist(member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        Member member = em.find(Member.class,id);
//        return Optional.ofNullable(member);
//    }
//
//    @Override
//    public Optional<Member> findByEmail(String email) {
//        List<Member> result = em.createQuery("select m from Member m where m.email = :email", Member.class)
//                .setParameter("email", email)
//                .getResultList();
//        return result.stream().findAny();
//    }
//    @Override
//    public Optional<Member> findByName(String name) {
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name",Member.class)
//                .setParameter("name",name)
//                .getResultList();
//        return result.stream().findAny();
//    }

//    @Override
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m",Member.class)
//                .getResultList();
//    }

//}