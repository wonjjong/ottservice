package wonjjong.dev.ottservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adk")
public class AdkController {

    @GetMapping({"/index","","/"})
    @Operation(summary = "Admin index page")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/tables")
    public String tables() {
        return "admin/tables";
    }
}
