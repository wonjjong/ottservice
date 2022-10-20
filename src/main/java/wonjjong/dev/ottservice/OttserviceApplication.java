package wonjjong.dev.ottservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import wonjjong.dev.ottservice.domain.user.Role;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.domain.user.UserRepository;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class OttserviceApplication  implements CommandLineRunner {

    private final UserRepository userRepository;

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

	@Override
	public void run(String... args) {
        for (int i = 0; i < 50; i++) {
            User user = User.builder()
                    .email(i + "@gmail.com")
                    .name("wonjjong"+ i)
                    .password("pw"+i)
                    .role(Role.USER).build();
            userRepository.save(user);
        }
	}

    public static void main(String[] args) {
        SpringApplication.run(OttserviceApplication.class, args);
    }

}
