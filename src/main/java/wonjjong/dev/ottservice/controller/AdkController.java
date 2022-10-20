package wonjjong.dev.ottservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.domain.user.UserRepository;
import wonjjong.dev.ottservice.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adk")
public class AdkController {

    private final UserService userService;
    @GetMapping({"/index","","/"})
    @Operation(summary = "Admin index page")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/tables")
    public String tables() {
        return "admin/tables";
    }

    @GetMapping("/userList")
    public String userList(Model model, Pageable pageable) {
        model.addAttribute("users", userService.userList(pageable));
        return "admin/userList";
    }

    @GetMapping("/userList-api")
    @ResponseBody
    public List<User> userListApi(Pageable pageable) {
        List<User> users = userService.userList(pageable);
        return userService.userList(pageable);
    }
}
