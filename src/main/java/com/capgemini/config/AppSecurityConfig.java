package com.capgemini.config;

import com.capgemini.filter.AppFilter;
import com.capgemini.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

   @Autowired
    private LibrarianService librarianService;

   @Autowired
    private AppFilter appFilter;

   @Bean
   public PasswordEncoder pwdEncoder(){
       return  new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationProvider authProvider(){

       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

       authProvider.setUserDetailsService(librarianService);
       authProvider.setPasswordEncoder(pwdEncoder());

       return  authProvider;
   }

   @Bean
   public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {

       return  config.getAuthenticationManager();

   }

  @Bean
   public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {

//        return http.csrf().disable()
//               .authorizeHttpRequests()
//               .requestMatchers("/login","/register").permitAll()
//               .anyRequest().authenticated()
//               .and()
//               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//               .and()
//               .authenticationProvider(authProvider())
//               .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class).build();

      http.csrf(csrf -> csrf.disable()) // Disable CSRF for stateless authentication
              .authorizeHttpRequests(auth -> auth
                      .requestMatchers("/login", "/register").permitAll() // Public endpoints
                      .anyRequest().authenticated() // Secure all other endpoints
              )
              .sessionManagement(sess ->
                      sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session
              )
              .authenticationProvider(authProvider()) // Custom authentication provider
              .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

      return http.build();

   }
}
