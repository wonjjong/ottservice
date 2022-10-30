package wonjjong.dev.ottservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wonjjong.dev.ottservice.dto.UserSaveForm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysDo(print()).build();
    }

    @Test
    void UserSaveForm_validation_테스트() throws Exception{
        //given
        UserSaveForm userSaveForm = new UserSaveForm();
        userSaveForm.setName("wonjjong");
        userSaveForm.setPassword("asdwer");

        //when
        /*ResultActions resultActions = mockMvc.perform(post("/user/signup")
                .content(s)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());*/
        ResultActions resultActions = mockMvc.perform(post("/user/signup")
                .content(objectMapper.writeValueAsString(userSaveForm))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
        ).andDo(print());

        //then
//        resultActions.andExpect(status().isPermanentRedirect());
        /*.
                      andExpect(status().isOk())
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                     .andExpect(jsonPath("$.username", is("sa1341")))
        * */
        /* .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/designView"));*/
    }

}