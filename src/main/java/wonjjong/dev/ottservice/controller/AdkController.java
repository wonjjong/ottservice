package wonjjong.dev.ottservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdkController {

    @GetMapping("/adk")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/adk2")
    public String index2() {
        return "admin/layout/layout";
    }
}
