package com.pshandy.rentservice.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/homepage");

        http.authorizeHttpRequests()
                .requestMatchers("/user/wish*", "/moderator/wish*", "/moderator/wish/*", "/user/profile*").hasRole("USER")
                .requestMatchers("/login*", "/logout*", "/user/registration*")
                .permitAll();
//        http.authorizeHttpRequests()
//                .requestMatchers("/user/wish*").hasRole("USER")
//                .requestMatchers("/login*", "/logout*", "/user/registration*")
//                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}
