package wonjjong.dev.ottservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import wonjjong.dev.ottservice.service.CustomOAuth2UserService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/**/*.png")
                .antMatchers("/**/*.js")
                .antMatchers("/**/*.css")
                .antMatchers("/h2-console/**")
                .antMatchers("/favicon.ico");
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/home/**", "/").permitAll()
                .antMatchers("/order/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/home/login")
                    .loginProcessingUrl("/home/loginProcess")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/home/index")
                    .failureUrl("/home/login")
                .and()
                    .logout()
                    .logoutUrl("/home/logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID","remember-me")
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/") // 기본값이 / 임
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                ;
        return http.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/adk/login").permitAll()
                .antMatchers("/adk/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/adk/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/") // 기본값이 / 임
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }
}
