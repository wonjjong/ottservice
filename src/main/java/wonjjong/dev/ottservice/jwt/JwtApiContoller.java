package wonjjong.dev.ottservice.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class JwtApiContoller {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @GetMapping({"", "/"})
    public String apiMain() {
        return "home/api_test";
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        log.info("username = {} , password = {} " , jwtRequest.getUsername(), jwtRequest.getPassword());
        authentication(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authentication(String username, String password) throws Exception {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException ex) {
            throw new DisabledException("USER_DISABLED", ex);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        }

    }

}
