package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// 디비의 컬럼네임이 다르면 @Column name 지정하면된다.
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;
    // 스프링이 엔티티메니저를 만들어준다. 자동으로 디비통신을 알아서 해준다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPQL이라는 걸 따로 짜야한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // 객체를 대상으로 쿼릐를 보낸다.
    }
}
