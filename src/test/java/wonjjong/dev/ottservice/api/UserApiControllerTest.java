package wonjjong.dev.ottservice.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import wonjjong.dev.ottservice.domain.user.User;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserApiControllerTest {

    @BeforeAll
    void before() {
        User user = new User();
        user.setId(1L);
        user.setName("wonjjong.dev");

    }

    @Test
    void getMemberById() {
        //given
        //when
        //then

    }

}