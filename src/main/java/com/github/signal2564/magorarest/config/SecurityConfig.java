package com.github.signal2564.magorarest.config;

import com.github.signal2564.magorarest.config.auth.CustomAuthHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ANON_KEY = "anon-key";

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public UserDetailsService acquireUserDetailsService() {
        return new InMemoryUserDetailsManager(
                Collections.singletonList(
                        new User("admin", "admin",
                                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"))));
    }

    @Bean
    public Filter acquireAuthHeaderFilter() {
        return new CustomAuthHeaderFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous().key(ANON_KEY).authorities("ROLE_USER").and()
                .authorizeRequests().anyRequest().permitAll().and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(acquireAuthHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}