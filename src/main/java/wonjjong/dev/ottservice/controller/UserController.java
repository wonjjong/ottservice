package wonjjong.dev.ottservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.service.CustomUserDetailsService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/user/signup")
    public String signup(User user) {
        Long userId = customUserDetailsService.saveUser(user);
        log.info("회원가입 성공, ID ={} ", userId);
        return "redirect:/";
    }

}
