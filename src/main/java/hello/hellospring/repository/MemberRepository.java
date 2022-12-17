package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); // 회원저장
    Optional<Member> findById(Long id); // 아이디로 회원찾기
    Optional<Member> findByName(String name); // 이름으로 회원찾기
    List<Member> findAll(); // 지금까지의 모든회원 반환하기
}
