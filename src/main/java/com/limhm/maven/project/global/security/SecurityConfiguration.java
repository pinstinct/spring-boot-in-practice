package com.limhm.maven.project.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 클래스가 스프링 부트 컴포넌트 스캔에 감지되어 설정 내용이 반영되도록 함
public class SecurityConfiguration {  // WebSecurityConfigurerAdapter deprecate

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        authorize -> authorize.requestMatchers("/api/v1/users/login").permitAll()
            .anyRequest().authenticated()
    );
    return http.build();
  }
}
