package config;

import example.repository.MemberRepository;
import example.repository.MemoryMemberRepository;
import example.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public SpringConfig(MemberRepository memberRepository, PasswordEncoder passwordEncoder, WebSecurity web)throws Exception {

        web.ignoring().antMatchers("/h2-console/**");
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository, passwordEncoder);
    }







//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}

/**클래스명 앞에 @Configuration을 등록하면 스프링이 설정 파일임을 인식, 컨테이너에 스프링 빈을 등록할 준비를 합니다.
@Bean을 사용하여 다음의 메서드를 호출해 스프링 빈이 등록됩니다. memberService메서드가 호출되면 MemberService 객체가 생성되고,
이때 @Bean을 통해 컨테이너에 등록된 레포지토리가 매개변수로 회원 서비스 객체와 연결(@Autowired와 비슷),
회원 서비스 객체가 반환됩니다(스프링 빈으로 등록됩니다).
컨트롤러는 컴포넌트 스캔과 동일하게 작동합니다. 즉, @Controller로 스프링 컨테이너에 등록, @Autowired로 스프링 빈에 등록된 객체와 연결됩니다.
위와 같이 설정 파일(자바 코드)을 직접 등록하여 스프링 빈을 등록할 수 있습니다.**/