package wonjjong.dev.ottservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @GetMapping({"","/","/index"})
    public String home() {
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "home/signup";
    }
}
