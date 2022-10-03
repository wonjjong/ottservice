package wonjjong.dev.ottservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/index")
    public String home() {
        return "home/index";
    }

    /*http://localhost:8080/login/oauth2/code/google */
}
