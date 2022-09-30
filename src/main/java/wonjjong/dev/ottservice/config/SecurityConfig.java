package wonjjong.dev.ottservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/resources**");
    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
             /*   .antMatchers("/adk/**").permitAll()*/
              /*  .antMatchers("/admin/**").hasAnyRole("ADMIN") --> 여기때문에 css 적용 안되었음*/
                .antMatchers("/order/**").hasAnyRole("USER");
     /*   http.csrf().disable()
                .authorizeRequests(authorize ->
                        authorize.antMatchers("/**").permitAll()
                        .anyRequest().permitAll()
                );*/

        return http.build();
    }

}
