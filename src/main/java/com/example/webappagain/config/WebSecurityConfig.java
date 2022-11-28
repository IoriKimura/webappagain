package com.example.webappagain.config;

import com.example.webappagain.models.Employee;
import com.example.webappagain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private final UserService userService;

    public WebSecurityConfig(UserService userService){
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/registration", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());
    }

}