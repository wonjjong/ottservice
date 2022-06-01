package wonjjong.dev.ottservice;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class OttserviceApplicationTests {

	MockMvc mockMvc;
	@Autowired
	WebApplicationContext context;

	@BeforeAll
	void contextLoads() {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName
			);
		}
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void googleOauthTest() {

	}

}
