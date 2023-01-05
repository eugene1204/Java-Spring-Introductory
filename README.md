# Java-Spring-Introductory
# 프로젝트 환경 설정 
## 프로젝트 생성
[spring initializer 다운받기](https://start.spring.io/)
![](https://velog.velcdn.com/images/uuzziinn/post/550aca2e-dae5-4022-a24f-b01523ec022e/image.png)

## 라이브러리 살펴보기
### 스프링 부트 라이브러리 
- spring-boot-starter-web 
  - spring-boot-starter-tamcat : 톰켓(웹서버)
  - spring-webmvc : 스프링 웹MVC
  - spring-boot-starter-tymeleaf : 타임리프 템플릿 엔진(View)
  - spring-voot-starter(공통) : 스프링 부트 + 스프링코어 + 로깅 
    - spring-boot
        - spring-core
    - spring-boot-starter-logging
        - logback, slf4j
### 테스트 라이브러리
- spring-boot-starter-test 
   - junit : 테스트 프레임워크 
   - mockito : 목 라이브러리 
   - assertj : 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리 
   - spring-test : 스프링 통합 테스트 지원 
   
## View 환경 설정 
### Welcome Page 만들기
#### 정적 페이지 
스프링 부트가 제공하는 Welcome Page 기능 
- static/index.html을 올려두면 Welcome Page 기능을 제공한다. 
- localhost:8080을 통해 확인가능
```
// resources/static/index.html
<!DOCTYPE html>
<html>

# 스프링 웹개발 기초

<head>
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
Hello
<a href="/hello">hello</a>
</body>
</html>
```
![](https://velog.velcdn.com/images/uuzziinn/post/dbabbdc6-0782-4e89-b308-3e88ee820d35/image.png)


### thymeleaf 템플릿 엔진 
```
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
```
```
// resources/templates/hello.html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p th:text="'안녕하세요. ' + ${data}" >안녕하세요. 손님</p>
</body>
</html>
```
![](https://velog.velcdn.com/images/uuzziinn/post/255b0934-f1ce-4763-a9f2-294daeeb266c/image.png)

### 동작환경 그림
- 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버(```viewReslover```)가 화면을 찾아서 처리한다. 
  - 스프링 부트 템플릿엔진 기본 viewName 매핑 
  - ```resources:templates/``` +[ViewName] + ```.html```
> 웹페이지에서 localhost:8080/hello 접속하면 ```helloController```을 통해 ```@GetMaapping```에서 hello에 해당한 코드 찾는다. 
이후 저장된 data와 함께 hello.html에 해당하는 코드 찾고 해당 데이터를 넣는다.
웹 브라우저에 hello.html을 반환한다.

![](https://velog.velcdn.com/images/uuzziinn/post/a617d004-a69f-4391-a8de-e64fa2688b6e/image.png)

## 빌드하고 실행하기
###콘솔로 이동
1. ./gradlew build
2. cd build.libs
3. java - jar hello-spring-0.0.1-SNAPSHOT.jar
4. 실행확인 

# 스프링 웹개발 기초
## 정적컨텐츠 
웰컴 페이지 파일을 웹브라우저에 그대로 내려준다.
```
 <!DOCTYPE HTML>
  <html>
  <head>
      <title>static content</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
<body>
정적 컨텐츠 입니다.
  </body>
  </html>
```
### 정적 컨텐츠 이미지
![image](https://user-images.githubusercontent.com/71254167/210830534-0fccd2ef-90e3-434c-966d-150270c7794e.png)


## MVC와 템플릿엔진
> jsp, php와 같이 서버에서 html을 동적으로 바꿔서 내려준다.
MVC = Model, View, Controller
- Model : 데이터와 비즈니스 로직을 관리한다
- View : 레이아웃과 화면을 처리한다. 
- Controller : 비즈니스로직, 내부적인 내용을 처리하는데 집중한다, 명령을 모델과 뷰부분으로 라우팅한다.

### Controller
```
// hello.hellospring.controller;
 @Controller
  public class HelloController {
      @GetMapping("hello-mvc")
      public String helloMvc(@RequestParam("name") String name, Model model) {
          model.addAttribute("name", name);
          return "hello-template";
      }
}
```
### View
```
// resources/templates/hello-template.html
<html xmlns:th="http://www.thymeleaf.org">
  <body>
  <p th:text="'hello ' + ${name}">hello! empty</p>
  </body>
</html>
```
### MVC, 템플릿 엔진 이미지
![image](https://user-images.githubusercontent.com/71254167/210834377-8705be48-b115-4e0f-b001-5773b60c70af.png)

## API
### @ResponseBody 문자반환
- ```@ResponseBody``` 를 사용하면 뷰 리졸버( ```viewResolver``` )를 사용하지 않음
- 대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)
```
@Controller
  public class HelloController {
      @GetMapping("hello-string")
      @ResponseBody
      public String helloString(@RequestParam("name") String name) {
          return "hello " + name;
      }
}
```
### ResponseBody 객체 반환 
- ```@ResponseBody```를 사용하고, 객체를 반환하면 객체가 JSON으로 변환된다. 
```
@Controller
  public class HelloController {
      @GetMapping("hello-api")
      @ResponseBody
      public Hello helloApi(@RequestParam("name") String name) {
          Hello hello = new Hello();
          hello.setName(name);
          return hello;
      }
      static class Hello {
          private String name;
          public String getName() {
              return name;
}
          public void setName(String name) {
              this.name = name;
} }
}
```
>json이라는 데이터구조 포멧으로 클라이언트에게 데이터를 내려준다.
뷰나 리엑트가 api, 서버끼리 데이터를 흐를때 사용한다. \
@ResponseBody는 controller없이 json 방식으로 데이터를 넘겨 

### ```@ResponseBody``` 사용원리
- ```@ResponseBody``` 를 사용
    - HTTP의 BODY에 문자 내용을 직접 반환
    - ```viewResolve```r 대신에 ```HttpMessageConverter``` 가 동작
    - 기본 문자처리: ```StringHttpMessageConverter```
    - 기본 객체처리: ```MappingJackson2HttpMessageConverter```
    - byte 처리 등등 기타 여러 ```HttpMessageConverter```가 기본으로 등록되어 있음
![image](https://user-images.githubusercontent.com/71254167/210838913-884a2da1-9ee0-4a36-828f-8665d211a4ef.png)

# 회원 관리 예제 - 백엔드 개발
## 비즈니스 요구사항 정리
데이터 : 회원아이디, 이름
기능 : 회원등록, 조회
아직데이터 저장소가 선정되지 않음 (가상시나리오) -DB선정x

### 일반적인 웹 애플리케이션 계층구조 
- 컨트롤러 : 웹 MVC의 컨트롤러 역할 
- 서비스 : 핵심 비즈니스 로직 구현
- 리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인 : 비즈니스 도메인 객체 
ex) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리된다. 
![](https://velog.velcdn.com/images/uuzziinn/post/a106ee1c-38e0-422d-8bdf-336d4e238ba6/image.png)


### 클래스 의존관계 
아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계
데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민중인 상황
개발을 진행하기 위해서 초기 개발단계에서 구현체로 가벼운 메모리 기반의
![](https://velog.velcdn.com/images/uuzziinn/post/d1ffbd85-11ef-4c83-9980-e450da6867ba/image.png)

## 회원도메인과 리포지토리 만들기
### 회원 객체
```
package hello.hellospring.domain;
public class Member {
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```
### 회원 리포지토리 인터페이스
```
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
```
### 회원리포지토리 메모리 구현체
```
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.*;
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
```

## 회원 리포지토리 테스트케이스 작성
개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다. 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는 단점이 있다. 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다
```
// src/test/java
package hello.hellospring.repository;
import java.util.List;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result);
//        assertThat.isEqualTo(result);
    }
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
```
- ```@AfterEach``` : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. ```@AfterEach``` 를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행한다. 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
- 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
>MemoryMemberRepositoryTest에 추가하기
각 테스트 코드가 실행된 후 afterEach()가 실행된다.
```
@AfterEach
    public void afterEach() {
        repository.clearStore();
    }
 ```
MemoryMemberRepository에 추가한다.
```
public void clearStore(){
        store.clear();
    }
   ```
테스트 코드를 먼저 작성한 다음 -> 클래스를 작성하는 방식을 TDD(테스트 주도 개발)이라고 한다.

## 회원 서비스 개발
> 서비스는 비즈니스에 맞는 용어를 사용한다. 
```
package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.Optional;
import java.util.List;
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증 memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // 전체회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```

## 회원 서비스 테스트 
### 기존에는 회원서비스가 메모리 회원 리포지토리를 직접 생성하게 했다. 
```public class MemberService {
      private final MemberRepository memberRepository =
                                            new MemoryMemberRepository();
}
```
### 회원 리포지토리의 코드가 회원서비스 코드를 DI가능하게 변경한다. 
> 생성자를 이용해서 DI를 주입한다. 

```
public class MemberService {
      private final MemberRepository memberRepository;
      public MemberService(MemberRepository memberRepository) {
          this.memberRepository = memberRepository;
}
... }
```

### 회원 서비스 테스트
- ```@BeforeEach``` : 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
```
package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MemberServiceTest {
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void join() {
        // given : 상황 주어짐
        Member member = new Member();
        member.setName("hello");
        // when : 이걸 실행했을때
        Long saveId = memberService.join(member);
        // then : 이게 답으로 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void findMembers() {
    }
    @Test
    void findOne() {
    }
}
```
> 기존에는 회원 서비스가 메모리회원 리포지토리를 직접 생성하게 한다. 
따라서 MemberServiceTest에 beforeEach() 코드를 추가한다. 

```
@BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
```

# 스프링 빈과 의존관계
## 스프링빈을 등록하는 2가지 방법 
> - 컴포넌트 스캔과 자동 의존관계 설정
- 자바 코드로 직접 스프링 빈등록하기 

## 컴포넌트 스캔과 자동 의존 관계 설정
### 스프링 빈을 등록하고, 의존관계 설정하기
회원컨트롤러가 회원서비스와 회원리포지토리를 사용할 수 있게 의존관계를 준비하자 
### 컴포넌트 스캔 원리
- ```@Component``` 애노테이션이 있으면 스프링 빈으로 자동 등록된다. 
- ```@Controller``` 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다
- ```@Component```를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다. 
  - ```@Controller``` 
  - ```@Service```
  - ```@Repository```
    

#### 회원 컨트롤러에 의존관계 추가 
- 생성자에``` @Autowired``` 가 있으면 연관된 객체를 스프링 컨테이너에 찾아서 넣어준다. 이렇게 객체의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라고한다. 
- 이전 테스트에서는 개발자가 직접 주입했고, 여기서는 ```@Autowired```에 의해서 스프링이 주입해준다. 
```
// MemberController는 MemberService를 통해 회원가입을 하고 데이터를 조회한다.
// MemberController가 MemberService를 의존한다(의존관계에 있다)
public class MemberController {
    // 다른 컨트롤러에서 MemberService를 가져다가 쓸수 있다.
    // 따라서 new로 새로운 객체를 생성하지 않는다.
    // 그래서 하나를 생성해서 여러번 받아와서 쓰면된다.
    // 스프링 컨트롤러에 하나를 등록해서 받아서 사용하자
    private final MemberService memberService;

    // 연결은 생성자로
    // memberSerivce를 스프링 컨트롤러가 생성할때 만들어진다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
```


#### 에러발생 
***MemberService가 스프링 빈으로 등록되어 있지 않다***
<img width="664" alt="image" src="https://user-images.githubusercontent.com/71254167/210850877-ad948cf2-d8b6-489e-bb61-f704a8a3cb32.png">
참고: helloController는 스프링이 제공하는 컨트롤러여서 스프링 빈으로 자동 등록된다. 
```@Controller``` 가 있으면 자동 등록됨

### MemeberService를 스프링 빈 등록하는 방법 
```
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
  	...
}
```
참고 : 생성자에 ```@Autowired```를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다. 생성자가 1개만 있으면 ```@Autowired```는 생략할 수 있다. 

### MemoryMemberRepository 스프링 빈 등록 
```
@Repository
public class MemoryMemberRepository implements MemberRepository{
	...
}
```

### 스프링 빈 등록 이미지 
- memberService와 memberRepository가 스프링 컨테이너에 스프링 빈으로 등록되었다. 
- @Autowired를 통해서 meberController와 memberService를 연결해주었다 (DI, 의존성 주입) 
<img width="664" alt="image" src="https://user-images.githubusercontent.com/71254167/210850694-c4ff5380-0775-46d2-8f55-fe5972343cc7.png">


## 자바 코드로 직접 스프링 빈등록하기 
- 회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거하고 진행한다.

```
package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

> 참고
- XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않는다.
- DI에는 필드주입, setter주입, 생성자 주입 이렇게 3가지 방법이 있다. 의존 관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다
- 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
	- ```MemoryMemberRepository```를 다른 리포지토리로 변경하는데 기존의 코드를 변경하지 않고 바꾸기 위해서 구현 클래스를 변경하는 경우
- ```@Autowired``` 를 통한 DI는 ```helloController``` , ```memberService``` 등과 같이 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.

# 회원 관리 예제 - 웹 MVC 개발 
## 회원 웹 기능 - 홈 화면 추가
### 홈 컨트롤러 추가
> 컨트롤러가 정적파일보다 우선순위가 높다.
```
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```
### 회원 관리용 홈
```

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <div>
        <h1>Hello Spring</h1> <p>회원 기능</p>
        <p>
            <a href="/members/new">회원 가입</a> <a href="/members">회원 목록</a>
        </p> </div>
</html>
```

## 회원 웹 기능 - 등록 
### 회원 등록 폼 컨트롤러 
```
@Controller
    public class MemberController {
        private final MemberService memberService;
        @Autowired
        public MemberController(MemberService memberService) {
            this.memberService = memberService;
        }
        @GetMapping(value = "/members/new")
        public String createForm() {
            return "members/createMemberForm";
        }
}
```

### 회원 등록 폼 HTML
```
// resources/templates/members/createMemberForm 

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
  <form action="/members/new" method="post">
    <div class="form-group">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" placeholder="이름을 입력하세요"> </div>
    <button type="submit">등록</button> </form>
</div> <!-- /container -->
</body>
</html>
```

## 회원 등록 컨트롤러 
### 웹 등록 화면에서 데이터를 전달 받을 폼 객체
```
package hello.hellospring.controller;
  public class MemberForm {
      private String name;
      public String getName() {
          return name;
}
      public void setName(String name) {
          this.name = name;
} }
```

### 회원 컨트롤러에서 회원을 실제 등록하는 기능
```
@PostMapping(value = "/members/new")
  public String create(MemberForm form) {
      Member member = new Member();
      member.setName(form.getName());
      memberService.join(member);
      return "redirect:/";
}
```

## 회원 웹 기능 
### 조회 회원 컨트롤러에서 조회 기능
```
@GetMapping(value = "/members")
  public String list(Model model) {
      List<Member> members = memberService.findMembers();
      model.addAttribute("members", members);
      return "members/memberList";
}
```

### 회원 리스트 HTML
```
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <div>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>이름</th> </tr>
            </thead>
            <tbody>
            <tr th:each="member : ${members}">
                <td th:text="${member.id}"></td>
                <td th:text="${member.name}"></td>
            </tr>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->
</body>
</html>
```


# 스프링 DB 접근 기술
## H2 데이터베이스 설치 
>
- [h2데이터베이스 설치주소](http://h2database.com/html/main.html)
- 압축파일 풀어서 사용하는 곳에 옮기기
- 권한줘야함 
```
chmod 755 h2.sh
```
- 실행 ```./h2.sh```
- 처음에는 데이터 베이스 파일을 만들어줘야함
![업로드중..](blob:https://velog.io/505caf7b-2687-4e22-8a1c-f652890ea2b3)
- ```~/test.mv.db ```만들어졌는지 확인해봄
![](https://velog.velcdn.com/images/uuzziinn/post/8b50d313-56fa-4d2b-ba97-c98caeae8e1a/image.png)
- 테이블 만들기 
```
drop table if exists member CASCADE;
    create table member
    (
        id   bigint generated by default as identity,
        name varchar(255),
        primary key (id)
);
```
```
generated by default as identity
// 아이디값을 입력하지 않았을때 자동으로 값을 설정해준다. 
```
- 값 넣어보기
```
insert into member(name) values('string');
```
![](https://velog.velcdn.com/images/uuzziinn/post/ae4104bb-cf52-41fa-a12d-a5f5d3b672cd/image.png)

## 순수JDBC
> 
```
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class JdbcMemberRepository implements MemberRepository{
    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate(); // 실제 디비에 쿼리가 날아간다.
            rs = pstmt.getGeneratedKeys(); // Statement.RETURN_GENERATED_KEYS 와 매칭
            // id 1번, 2번,,, 값을 받아온다.
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 자원 릴리즈해줘야한다.
        }
    }
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } try {
        if (pstmt != null) {
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
```
### 스프링 설정 변경
- DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다.
```
package hello.hellospring;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
```
- 개발 폐쇄 원칙 (OCP, Open-Closed Principle)
	- 확장에는 열려 있고, 수정, 변경에는 닫혀있다. 
- 스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
- 회원을 등록하고 DB에 결과가 잘 입력되는지 확인하자.
- 데이터를 DB에 저장하므로 스프링 서버를 다시 실행해도 데이터가 안전하게 저장된다.
### 구현 클래스 추가 이미지
<img width="588" alt="image" src="https://user-images.githubusercontent.com/71254167/210855218-74f3c789-7b0a-4272-9863-99c217af106b.png">

### 스프링 설정 이미지 
<img width="660" alt="image" src="https://user-images.githubusercontent.com/71254167/210855380-0096940e-58ad-4b0f-b968-0c036eb8163a.png">

## 스프링 통합 테스트 
>스프링 컨테이너와 DB까지 연결한 통합 테스트를 진행해보자 
```MemberServiceIntegrationTest```를 실행시킨뒤 한번더 실행하게 된다면 오류 메시지가 뜬다(이때 ```@Transactional```은 주석처리한다) 왜냐하면 DB에 이미 데이터가 올라가있기 때문이다. 이때 발생하는 문제는 테스트를 여러번 실행할 수 없다는 것이다. 이를 방지 하기 위해서 ```@Transactional```을 사용한다. 
- ```@SpringBootTest``` : 스프링 컨테이너와 테스트를 함께 실행한다.
- ```@Transactional``` : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
```
package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired  MemberService memberService;
    @Autowired
    MemberRepository memberRepository ;
    @Test
    void join() throws Exception{
        // given : 상황 주어짐
        Member member = new Member();
        member.setName("hello");
        // when : 이걸 실행했을때
        Long saveId = memberService.join(member);
        // then : 이게 답으로 나와야함
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
```

## 스프링 JdbcTemplate
>- 순수 jdbc와 동일한 환경설정을 하면된다. 
- 스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다. 하지만 SQL은 직접 작성해야 한다.
```
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class JdbcTemplateMemberRepository implements MemberRepository{
   private final JdbcTemplate jdbcTemplate;
    @Autowired // 생성자가 하나면 오토와이어 생략가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        // SimpleJdbcInsert을 사용하면 쿼리문을 짤 필요가 없다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id) ;
        return result.stream().findAny();
    }
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),name) ;
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }
    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }
}
```
### JdbcTemplate을 사용하도록 스프링 설정 변경
```
package hello.hellospring;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
```

## JPA 
> JdbcTemplate를 사용하면 반복적인 코드는 줄여주지만 sql 쿼리는 개발자가 직접 짜야한다.. 
- JPA는 기본의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
- JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다. 
-JPA를 사용하면 개발 생산성을 크게 높일 수 있다. 
### build.gralde 파일에, JPA, h2 데이터베이스 관련 라이브러리 추가하기
```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```
### 스프링 부트에 JPA 설정 추가
- ```show-sql``` : JPA가 생성하는 SQL을 출력한다.
- ```ddl-auto``` : JPA는 테이블을 자동으로 생성하는 기능을 제공하는데 none를 사용하면 해당 기능을 끈다.
	- ```create```를 사용하면 엔티티 정보를 바탕으로 테이블도 직접 생성해 준다.
```
 spring.jpa.show-sql=true
 spring.jpa.hibernate.ddl-auto=none
```
### JPA 엔티티 매핑 
```
package hello.hellospring.domain;
import javax.persistence.*;
@Entity
// db가 id를 자동으로 생성하는 것
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```
### JPA 회원 리포지토리 
```
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
```
### 서비스 계층에 트랜잭션추가 
- org.springframework.transaction.annotation.Transactional 를 사용하자.
- 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
- JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
```
import org.springframework.transaction.annotation.Transactional
  @Transactional
  public class MemberService {}
```
### JPA를 사용하도록 스프링 설정 변경
```
```

## 스프링 데이터 JPA
> - 스프링 부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야할 코드도 확연히 줄어든다. 여기에 스프링 데이터 JPA를 사용하면, 기존의 한계를 넘어 마치 마법처럼, 리포지토리에 구현 클래스 없이 인터페이스만으로 개발을 완료할 수 있다. 그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공합니다.
- 스프링 부트와 JPA라는 기반 위에, 스프링 데이터 JPA라는 환상적인 프레임워크를 더하면 개발이 정말 즐거워집니다. 지금까지 조금이라도 단순하고 반복이라 생각했던 개발 코드들이 확연하게 줄어듭니다.
- 따라서 개발자는 핵심 비즈니스 로직을 개발하는데, 집중할 수 있습니다.
- 실무에서 관계형 데이터베이스를 사용한다면 스프링 데이터 JPA는 이제 선택이 아니라 필수 입니다.
- 단, 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술입니다. 따라서 JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 합니다.
### 스프링 데이터 JPA 회원 리포지토리
```
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    Optional<Member> findByName(String name);
}
```
### 스프링 데이터 JPA 회원 리포지토리를 사용하도록 스프링 설정 변경
```
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
```
### 스프링 데이터 JPA 제공 클래스

### 스피링 제이터 JPA 제공 기능 
- 인터페이스를 통한 기본적인 CRUD
- findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공 
- 페이징 기능 자동 제공
JPA~가 CrudRepository가 기본적인 메서드를 다 가지고 있다. 
ex ) findAll(), findID(), save()...
따라서 가져다 쓰면된다. 하지만 findName()은 없어서 가져와야함 
Member에서 name같은거는 공통으로 만들수없는것 findByName()이라고하면 JPA가 
// JPQL select m from Member m where m.name= ?
와 같이 쿼리를 알아서 작성해준다. 
