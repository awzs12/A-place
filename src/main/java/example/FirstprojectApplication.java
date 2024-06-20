package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "example.repository")
@EntityScan(basePackages = {"example.entity"}) // 엔티티가 위치한 패키지 명시
@EnableWebSecurity
public class FirstprojectApplication {

	public static void main(String[] args)
	{

		SpringApplication.run(FirstprojectApplication.class, args);
	}

}
