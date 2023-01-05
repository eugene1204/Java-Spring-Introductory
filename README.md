# Java-Spring-Introductory
# 프로젝트 환경 설정 
## 프로젝트 생성
> 
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
> #### 정적 페이지 
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
>
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

### 동작환경 그림
- 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버(```viewReslover```)가 화면을 찾아서 처리한다. 
  - 스프링 부트 템플릿엔진 기본 viewName 매핑 
  - ```resources:templates/``` +[ViewName] + ```.html```

![](https://velog.velcdn.com/images/uuzziinn/post/255b0934-f1ce-4763-a9f2-294daeeb266c/image.png)
웹페이지에서 localhost:8080/hello 접속하면 ```helloController```을 통해 ```@GetMaapping```에서 hello에 해당한 코드 찾는다. 
이후 저장된 data와 함께 hello.html에 해당하는 코드 찾고 해당 데이터를 넣는다.
웹 브라우저에 hello.html을 반환한다.
![](https://velog.velcdn.com/images/uuzziinn/post/a617d004-a69f-4391-a8de-e64fa2688b6e/image.png)

## 빌드하고 실행하기
> 콘솔로 이동
1. ./gradlew build
2. cd build.libs
3. java - jar hello-spring-0.0.1-SNAPSHOT.jar
4. 실행확인 

