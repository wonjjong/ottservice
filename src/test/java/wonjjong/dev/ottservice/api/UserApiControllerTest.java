package wonjjong.dev.ottservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.dto.UserSaveForm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* @AutoConfigureTestDatabase 애노테이션은 기존에 설정되어 있는 데이터베이스 설정 대신에
* 테스트용 인메모리를 강제로 사용하는 것(Replace.ANY)이 기본 설정으로 되어 있다.
* @AutoconfigureTestDatabase가 포함되는 애노테이션이 사용되는 테스트 환경에서는
* 다음과 같이 기본 설정값을 Replace.NONE로 변경해야 p6spy가 의도했던 대로 제대로 동작한다.
* */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    @BeforeAll
    void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysDo(print()).build();
    }



}