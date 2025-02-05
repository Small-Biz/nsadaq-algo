package com.smallbiz.nasdaqalgo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
*/
@Configuration
//@EnableWebSecurity(debug=true)
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
/*
    //https://stackoverflow.com/questions/59808537/cookies-headers-are-present-but-cookies-are-not-stored-in-browser
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .cors().and()
        .csrf(csrf -> csrf.disable())
//		.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeRequests()
        // Our public endpoints
        .antMatchers("/api/**").permitAll()
        .antMatchers("/auth/**").permitAll()
        .antMatchers("/stripe/**").permitAll()
        // Our private endpoints
        .antMatchers("/user/**").hasRole("USER")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()

//                // defining exception handling
//                .and().exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                // setting custom access denied handler for not authorized request
//                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                // setting custom entry point for unauthenticated request/        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())

        .and()
        .httpBasic();


//        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
*/

}

