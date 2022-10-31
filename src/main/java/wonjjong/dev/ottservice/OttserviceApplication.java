package wonjjong.dev.ottservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import wonjjong.dev.ottservice.domain.user.Role;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.domain.user.UserRepository;
import wonjjong.dev.ottservice.service.CustomUserDetailsService;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class OttserviceApplication  implements CommandLineRunner {

    private final UserRepository userRepository;
	private final CustomUserDetailsService customUserDetailsService;

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

	@Override
	@Profile("dev")
	public void run(String... args) {
        for (int i = 0; i < 1; i++) {
            User user = User.builder()
                    .email(i + "@gmail.com")
                    .name("wonjjong"+ i)
                    .password("pw"+i)
                    .role(Role.USER).build();
//			userRepository.save(user);
           customUserDetailsService.saveUser(user);
        }
		/*
		* 진도처리 /수료처리 / 설문 / 시험 / 성적조회/ 수강이력 / 쪽지함 / 챗봇
		* */
	}

    public static void main(String[] args) {
        SpringApplication.run(OttserviceApplication.class, args);
    }

}
