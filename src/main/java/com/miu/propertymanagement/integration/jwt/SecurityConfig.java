package com.miu.propertymanagement.integration.jwt;

import com.miu.propertymanagement.domain.enums.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                        .requestMatchers("/api/property").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/message/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/property/*").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/property/*").hasRole(UserType.Owner.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/property/*").hasRole(UserType.Owner.toString())
                        .requestMatchers("/api/owner/**").hasRole(UserType.Owner.toString())
                        .requestMatchers("/api/customer/**").hasRole(UserType.Customer.toString())
                        .requestMatchers("/api/admin/**").hasRole(UserType.Admin.toString())

                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
