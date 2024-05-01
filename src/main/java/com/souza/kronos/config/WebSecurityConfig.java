package com.souza.kronos.config;

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
    @SuppressWarnings("deprecation")
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(auth -> {
            auth.requestMatchers("/js/**").permitAll();
            auth.requestMatchers("/css/**").permitAll();
            auth.requestMatchers("/uploads/**").permitAll();
            auth.anyRequest().authenticated();

        });

        http.formLogin(form -> {
            form.loginPage("/login")
            .permitAll();
            
        });

        http.logout(logout -> {
            logout.logoutSuccessUrl("/login");
        });
            
		return http.build();
	}
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
