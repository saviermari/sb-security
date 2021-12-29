package com.smari.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Role Based Access Restrictions
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    /**
     * Authentication
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.jdbcAuthentication().dataSource(dataSource)
                .withDefaultSchema()
                .withUser("savier")
                .password(encoder.encode("welcome1"))
                .roles("USER")
                .and()
                .withUser("mounish")
                .password(encoder.encode("welcome1"))
                .roles("ADMIN");
    }

    /**
     * Authorization
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeHttpRequests()
                .antMatchers("/admin")
                .hasRole("ADMIN")
                .antMatchers("/user")
                .hasAnyRole("ADMIN","USER")
                .antMatchers("/")
                .permitAll()
                .antMatchers("/h2-console/**")
                .permitAll()
                .and()
                .formLogin();
        //only for ht if security is enbled
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
