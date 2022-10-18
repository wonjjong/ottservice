package wonjjong.dev.ottservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wonjjong.dev.ottservice.dto.UserSaveForm;
import wonjjong.dev.ottservice.service.CustomUserDetailsService;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping({"","/","/index"})
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

    @PostMapping("/user/signup")
    public String signup(@Validated @ModelAttribute UserSaveForm userSaveForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            //응답코드 확인 400이면 Bad Request, 500이면 서버에러
            return "redirect:/user/signup";
        }

        /*Long userId = customUserDetailsService.saveUser(User.builder().email(user.getEmail()).name(user.getName()).);
        redirectAttributes.addAttribute("userId",userId);*/
        redirectAttributes.addAttribute("status",true);

//        log.info("회원가입 성공, ID ={} ", userId);

        return "redirect:/";
    }

    @GetMapping("/categories")
    public String categories() { return "home/categories";}

    @GetMapping("/blog")
    public String blog() { return "home/blog";}

    @GetMapping("/blog-details")
    public String blogDetails() { return "home/blog-details";}
}
