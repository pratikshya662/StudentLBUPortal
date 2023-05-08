package com.pratikshya.StudentPortal;
import com.pratikshya.StudentPortal.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
    @EnableWebSecurity
    public class WebConfig {

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .build();
        }


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .requestMatchers("/anonymous*")
                    .anonymous()
                    .requestMatchers("/login","/register", "/assets/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .successHandler(myAuthenticationSuccessHandler())
                    .failureUrl("/login?error=true")
                    .and()
                    .logout()
                    .deleteCookies("JSESSIONID")
                    .and()
                    .rememberMe()
                    .key("uniqueAndSecret")
                    .tokenValiditySeconds(86400)
                    .and()
                    .csrf()
                    .disable();
            return http.build();
        }

        @Bean
        public AuthenticationSuccessHandler myAuthenticationSuccessHandler()
        {
            return new MySimpleUrlAuthenticationSuccessHandler();
        }


}

