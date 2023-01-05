package hello.hellospring;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
//    private final DataSource dataSource;
//    private final EntityManager em;
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.em = em;
//        this.dataSource = dataSource;
//    }
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//
//    }
}
