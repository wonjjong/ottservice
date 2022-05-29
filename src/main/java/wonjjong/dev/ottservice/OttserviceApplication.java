package wonjjong.dev.ottservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OttserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OttserviceApplication.class, args);
	}

}
