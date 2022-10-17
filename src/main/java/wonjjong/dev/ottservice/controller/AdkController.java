package wonjjong.dev.ottservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adk")
public class AdkController {

    @GetMapping({"/index","","/"})
    @Operation(summary = "Admin index page") // RestController가 아닌경우에는 해당 어노테이션을 추가해줘야하는 듯 하다.
    public String index() {
        return "admin/index";
    }

}
