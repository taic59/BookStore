package com.bookstore.javaspringdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {  
    http.csrf().disable().authorizeRequests()
    .antMatchers(HttpMethod.POST, "/users").permitAll()
    .antMatchers(HttpMethod.GET, "/books").permitAll()
    .antMatchers("/"
        ,"/swagger-ui.html"
        ,"/v2/api-docs"
        ,"/v3/api-docs/**"
        ,"/configuration/ui"
        ,"/swagger-resources/**"
        ,"/configuration/**"
        ,"/swagger-ui/**"
        ,"/webjars/**"
        ,"/h2-console/**").permitAll()
        .anyRequest().authenticated()
        .and().addFilterBefore(
            new JWTAuthenticationProcessingFilter(
                    "/login", 
                    authenticationManager()
            ),
            UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(
                new JWTGenericFilterBean(),
                UsernamePasswordAuthenticationFilter.class
        );
        http.headers().frameOptions().disable();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}