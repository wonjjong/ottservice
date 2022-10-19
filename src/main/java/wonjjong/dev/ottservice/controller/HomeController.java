package wonjjong.dev.ottservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wonjjong.dev.ottservice.config.CustomUserDetails;
import wonjjong.dev.ottservice.domain.user.Role;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.dto.UserSaveForm;
import wonjjong.dev.ottservice.service.CustomUserDetailsService;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
@Tag(name = "HomeController" , description = "프론트 메인 컨트롤러")
@Slf4j
public class HomeController {

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping({"","/","/index"})
    @Operation(summary="home", description = "home index 리턴")
    public String home() {
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userSaveForm", new UserSaveForm());
        return "home/signup";
    }

    @PostMapping("/signup") //Form login 방식
    public String signup(@Validated @ModelAttribute
                             @Parameter(description = "userSaveForm", required = true)
                             UserSaveForm userSaveForm,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            //응답코드 확인 400이면 Bad Request, 500이면 서버에러
            return "/home/signup";
        }

        Long userId = customUserDetailsService.saveUser(
                User.builder().email(userSaveForm.getEmail())
                        .name(userSaveForm.getName())
                        .password(userSaveForm.getPassword())
                        .role(Role.USER)
                        .build()
        /*        new User(
                    userSaveForm.getEmail(),
                    userSaveForm.getName(),
                    userSaveForm.getPassword(),
                    Role.USER)*/
        );

        log.info("회원가입 성공, ID ={} ", userId);
        redirectAttributes.addAttribute("userId",userId);
        redirectAttributes.addAttribute("status",true);

        return "redirect:/";
    }

    @GetMapping("/login/form/info")
    public void formLoginTest(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        log.info("principle: " + principal.getUsername());
    }

    @GetMapping("/login/auth/info")
    public void OAuthloginTest(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        log.info("principle: " + principal.getAttributes());
    }


    @GetMapping("/categories")
    public String categories() { return "home/categories";}

    @GetMapping("/blog")
    public String blog() { return "home/blog";}

    @GetMapping("/blog-details")
    public String blogDetails() { return "home/blog-details";}
}
