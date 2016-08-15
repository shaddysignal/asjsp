package com.github.signal2564.magorarest.config.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomAuthHeaderFilter extends GenericFilterBean {
    private static final String TOKEN_HEADER = "X_ACCESS_TOKEN";

    @Autowired
    private Environment env;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(TOKEN_HEADER);

        try {
            if (authToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Claims claims = Jwts.parser().setSigningKey(env.getProperty("jwt.secret"))
                        .parseClaimsJws(authToken).getBody();

                UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (SignatureException ignored) {
            // just ignore, security context is empty, most likely
        }

        chain.doFilter(request, response);
    }
}
