package com.capgemini.filter;

import com.capgemini.service.JwtUtil;
import com.capgemini.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String username = null;

        //take request header

        String authHeader = request.getHeader("Authorization");
        //check token is present or not
        System.out.println("Auth Header ----" + authHeader);

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
            System.out.println("It is in Auth Header");
        }
        //verify token is valid or not

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("It is in 2nd");
            UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

            boolean isValid = jwtUtil.validateToken(token,userDetails);

            if(isValid){
                System.out.println("It is in 3rd");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        System.out.println("It has passed all checks");
        System.out.println(request);
        System.out.println(response);
        filterChain.doFilter(request,response);
    }
}
