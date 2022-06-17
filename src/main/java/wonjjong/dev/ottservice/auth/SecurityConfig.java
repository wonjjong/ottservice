package wonjjong.dev.ottservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wonjjong.dev.ottservice.domain.user.Role;
/*

//http://localhost:8080/login/oauth2/code/google
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        //패스워드 암호화 빈 생성
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/members/**","/image/**");    // /image/** 있는 모든 파일들은 시큐리티 적용을 무시한다.
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());    // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                .anyRequest().permitAll()
//                .and()					//추가
//                .oauth2Login()				// OAuth2기반의 로그인인 경우
//                .loginPage("/loginForm")		// 인증이 필요한 URL에 접근하면 /loginForm으로 이동
//                .defaultSuccessUrl("/")			// 로그인 성공하면 "/" 으로 이동
//                .failureUrl("/loginForm")		// 로그인 실패 시 /loginForm으로 이동
//                .userInfoEndpoint();	// 로그인 성공 후 사용자정보를 가져온다
//                .userService(customOAuth2UserService);	//사용자정보를 처리할 때 사용한다

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole((Role.USER.name()))
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint();
//                .userService(customOAuth2UserService);
    }
}
*/
