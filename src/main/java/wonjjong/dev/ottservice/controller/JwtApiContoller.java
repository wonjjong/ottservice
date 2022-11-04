package wonjjong.dev.ottservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtApiContoller {

    @GetMapping("/example-token")
    public String exampleToken() {
        return null;
    }
    
}
