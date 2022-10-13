package wonjjong.dev.ottservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wonjjong.dev.ottservice.aws.service.S3Service;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @Autowired
    private final S3Service s3Service;

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

    @GetMapping("/blog-details")
    public String blogDetails() {
        return "home/blog-details";
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
}
