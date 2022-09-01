package com.matcher.matcherApi.security.configuration;

import com.matcher.matcherApi.security.filter.AuthenticationFilterCustom;

import com.matcher.matcherApi.security.filter.AuthorizationCustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    private static final String[] ADMIN_LIST_URLS = {
            "/api/users**",
          //  "/api/history/**",
         //   "/api/timers/all/**",
         //   "/api/tasks/all/**"
    };
    private static final String[] WHITE_LIST_URLS = {
            "/",
            "/register",
            "/login",
            "/verifyRegistration*",
            "/resendVerifyToken*",
            "/api/token/refresh/**"
            // "/api/**"
    };

    private final UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       AuthenticationFilterCustom authenticationFilter= new AuthenticationFilterCustom(authenticationManager());
        // authenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(WHITE_LIST_URLS).permitAll().antMatchers(ADMIN_LIST_URLS).hasRole("ADMINISTRATOR").antMatchers("/api/**").authenticated();
        // http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationCustomFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
}