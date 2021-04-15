package com.haulmont.demoproject.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.haulmont.demoproject.model.Client;
import com.haulmont.demoproject.model.enums.Role;

import javax.servlet.http.Cookie;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private static final String USERNAME_PARAMETER = "email";
    private static final String CLIENT_ID_COOKIE_NAME = "uuid";

    public SecurityConfig(@Lazy @Qualifier("clientServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/login", "/registration").anonymous()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter(USERNAME_PARAMETER)
                .successHandler(((httpServletRequest, httpServletResponse, authentication) -> {
                    UUID id = ((Client) authentication.getPrincipal()).getId();

                    httpServletResponse.addCookie(new Cookie(CLIENT_ID_COOKIE_NAME, id.toString()));
                    httpServletResponse.sendRedirect("/");
                }))
                .and()
                .logout()
                .deleteCookies(CLIENT_ID_COOKIE_NAME)
                .clearAuthentication(true)
                .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
