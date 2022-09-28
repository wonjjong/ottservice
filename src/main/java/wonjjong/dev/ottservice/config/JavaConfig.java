package wonjjong.dev.ottservice.config;


//@Configuration
//@EnableWebSecurity
//public class JavaConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().disable()      // cors 비활성화
//                .csrf().disable()      // csrf 비활성화
//                .formLogin().disable() //기본 로그인 페이지 없애기
//                .headers().frameOptions().disable();
//    }
//}