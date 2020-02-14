package com.example.vaga.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeRequests().
                antMatchers(HttpMethod.GET, "/login/**").permitAll().
                antMatchers(HttpMethod.GET, "/pessoaws/**").hasAuthority("ADMIN","USER").
                antMatchers(HttpMethod.POST, "/pessoaws/**").hasAuthority("ADMIN").
                antMatchers(HttpMethod.DELETE, "/pessoaws/**").hasAuthority("ADMIN").
                anyRequest().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("comum").password("{noop}123456").roles("USER");
    }
}
