package wonjjong.dev.ottservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wonjjong.dev.ottservice.jwt.JwtAuthenticationEntryPoint;
import wonjjong.dev.ottservice.jwt.JwtRequestFilter;
import wonjjong.dev.ottservice.jwt.JwtUserDetailsService;
import wonjjong.dev.ottservice.service.CustomOAuth2UserService;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final JwtRequestFilter jwtRequestFilter;

    private final JwtUserDetailsService jwtUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    /*@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }*/

    /*
    * @Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}
*
* authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)).
    *
    *
    * */
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
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/*").permitAll()
                   /* .anyRequest().authenticated()*/
                .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .userDetailsService(jwtUserDetailsService);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/home/**", "/").permitAll()
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
    @Order(3)
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

/*
public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new TokenAuthFilter(authenticationManager));
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
}
*/
