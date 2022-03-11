package wonjjong.dev.ottservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MemberController {
    @RequestMapping("/members/new")
    public String createForm(Model model) {
        log.info("/members/new invoke");
        return "members/createMemberForm";
    }
}
