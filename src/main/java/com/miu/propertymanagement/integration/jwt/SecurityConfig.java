package com.miu.propertymanagement.integration.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    private Jwt jwtUtil = new Jwt();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtUtil);
        return http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((matcher) -> matcher
                                .requestMatchers("/**").permitAll()
//                        .requestMatchers("/api/login").permitAll()
//                        .requestMatchers("/api/register").permitAll()
//                        .requestMatchers("/api/property/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/customers/*").permitAll()
//                        .requestMatchers("/api/reservations/**").authenticated()
//                        .requestMatchers("/api/reservations/CheckIn").hasRole(UserType.Customer.toString())
//                        .requestMatchers("/api/reservations/CheckOut").hasRole(UserType.Customer.toString())
//                        .requestMatchers("/api/reservations/*").hasRole(UserType.Admin.toString())
//                        .requestMatchers("/api/products/**").hasRole(UserType.Admin.toString())
//                        .requestMatchers("/api/customers/**").hasRole(UserType.Admin.toString())

                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
