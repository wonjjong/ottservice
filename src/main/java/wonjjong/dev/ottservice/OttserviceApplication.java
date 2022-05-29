package wonjjong.dev.ottservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.domain.user.UserRepository;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class OttserviceApplication  implements CommandLineRunner {

    private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("wonjjong.dev@gmail.com");
		userRepository.save(user);
	}

    public static void main(String[] args) {
        SpringApplication.run(OttserviceApplication.class, args);
    }

}
