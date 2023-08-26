package com.demo.springsecurityclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private static final String[] WHITE_LIST_URLS = {
      "/hello",
      "/register",
      "/verifyRegistration**",
      "/resendVerifyToken**",
      "/resetPassword",
      "/savePassword**",
  };

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(WHITE_LIST_URLS).permitAll());
    return httpSecurity.build();
  }
}
