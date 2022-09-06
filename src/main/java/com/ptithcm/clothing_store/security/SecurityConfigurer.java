package com.ptithcm.clothing_store.security;

import com.ptithcm.clothing_store.security.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    final private JWTAuthorizationFilter authorizationFilter;

    @Autowired
    public SecurityConfigurer(JWTAuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/customer/login","/admin/login","/customer/create")
                .permitAll()
                .antMatchers("/customer/**")
                .hasAnyRole("CUSTOMER","SHIPPER","ADMIN")
                .antMatchers("/admin/*")
                .hasAnyRole("SHIPPER","ADMIN")
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authorizationFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
