package com.dev.craniumproperty.service.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.craniumproperty.util.JwtUtil;
import com.dev.craniumproperty.util.LibraryUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    // @Autowired
    // private final LibraryUtil libraryUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {

        logger.debug("Filtering request for JWT header verification");

        // try {
        //     String jwt = getJwtFromRequest(request);

        //     if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        //         String username = tokenProvider.getUserIdFromJWT(jwt);

        //         // UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        //         // UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
        //         //         (userDetails, null, userDetails.getAuthorities());
        //         // authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //         // SecurityContextHolder.getContext().setAuthentication(authentication);
        //     }
        // } catch (Exception ex) {
        //     logger.error("Could not set user authentication in security context", ex);
        // }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {

        logger.debug("Attempting to get token from request header");

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }
    
}
