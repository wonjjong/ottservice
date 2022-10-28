package wonjjong.dev.ottservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wonjjong.dev.ottservice.aws.service.S3Service;
import wonjjong.dev.ottservice.config.CustomUserDetails;
import wonjjong.dev.ottservice.domain.user.Role;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.dto.UserSaveForm;
import wonjjong.dev.ottservice.service.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
@Tag(name = "HomeController" , description = "프론트 메인 컨트롤러")
@Slf4j
public class HomeController {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    private final S3Service s3Service;

    @GetMapping({"","/","/index"})
    @Operation(summary="home", description = "home index 리턴")
    public String home() {
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/home/login";
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
                    Role.USER)
                    builder를 써야하는 이유
                    */ 
        );

        log.info("회원가입 성공, ID ={} ", userId);
        redirectAttributes.addAttribute("userId",userId);
        redirectAttributes.addAttribute("status",true);

        return "redirect:/";
    }

    @GetMapping("/login/form/info")
    @ResponseBody
    public Authentication formLoginTest(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) {
        //postman에서만 안되는 문제 발생
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication1 = securityContext.getAuthentication();
        authentication1.getPrincipal();
        return authentication1;
//        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
//        log.info("principle: " + principal.getUsername());
//        return principal.getUsername();
    }

    @GetMapping("/login/auth/info")
    @ResponseBody
    public void OAuthloginTest(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        log.info("principle: " + principal.getAttributes());
    }

    @GetMapping("/anime-details")
    public String animeDetails() {
        return "home/anime-details";
    }
    @GetMapping("/anime-watching")
    public String animeWatching(Model model) {
        model.addAttribute("videoPath", s3Service.getS3());
        return "home/anime-watching";
    }

    @GetMapping("/categories")
    public String categories() { return "home/categories";}

    @GetMapping("/blog")
    public String blog() { return "home/blog";}

    @GetMapping("/blog-details")
    public String blogDetails() { return "home/blog-details";}
}
