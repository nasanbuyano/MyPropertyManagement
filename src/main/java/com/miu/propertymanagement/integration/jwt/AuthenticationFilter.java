package com.miu.propertymanagement.integration.jwt;

import com.miu.propertymanagement.domain.enums.UserType;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {

    private  Jwt jwtUtil;

    public AuthenticationFilter(Jwt jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
            throws ServletException, IOException {
        try {
            final String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                filterchain.doFilter(request, response);
                return;
            }

            final String token = header.split(" ")[1].trim();

            if (!jwtUtil.validateToken(token)) {
                filterchain.doFilter(request, response);
                return;
            }

            String username = jwtUtil.getUserNameFromToken(token);
            UserType role = UserType.valueOf(jwtUtil.getRoleFromToken(token));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
                    java.util.Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterchain.doFilter(request, response);
        } catch (java.io.IOException | ServletException e) {
            e.printStackTrace();
        }
    }

}