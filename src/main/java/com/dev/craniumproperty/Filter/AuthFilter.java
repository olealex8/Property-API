package com.dev.craniumproperty.Filter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.dev.craniumproperty.entity.UserEntity;
import com.dev.craniumproperty.exception.ResultNotFoundException;
import com.dev.craniumproperty.repository.UserRepository;
import com.dev.craniumproperty.util.ConstantUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

@Component
public class AuthFilter extends GenericFilterBean {

    @Autowired
    UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("X-Content-Security-Policy", "script-src 'self'");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

        String authHeader = httpRequest.getHeader("Authorization");

        logger.info(authHeader);

        if (authHeader != null) {
            String[] authHeaderArr = authHeader.split("Bearer ");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];
                try {
                    Claims claims = Jwts.parser().setSigningKey(ConstantUtil.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
//                    if (claims.get("type").toString().compareTo("merchant") != 0) {
//                        httpRequest.setAttribute("officerId", Integer.parseInt(claims.get("officerId").toString()));
//                    }
//                    else {
                    httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
//                    }
                } catch (Exception e) {
                    try {
                        httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid / expired token");
                    } catch (java.io.IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    return;
                }
            } else {
                try {
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return;
            }
        } else {
            try {
                httpResponse.sendError((HttpStatus.FORBIDDEN.value()), "Authorization token must be provided");
            } catch (java.io.IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public UserEntity getUserFromToken(String token) {
        Map data = new HashMap();
        if (token != null) {
            try {
                Claims claims = Jwts.parser().setSigningKey(ConstantUtil.API_SECRET_KEY)
                        .parseClaimsJws(token).getBody();
                UserEntity user = userRepository.findOneByEmailAndDeleted(claims.get("userEmail").toString(), false);
                return user;
            } catch (Exception e) {
                logger.error("Error get user from token" + e);
                throw new ResultNotFoundException("invalid token / token expired");
            }
        }
        return null;
    }

    public String getToken(HttpServletRequest httpRequest) {
        if (httpRequest != null) {
            try {
                String authHeader = httpRequest.getHeader("Authorization");
                String[] authHeaderArr = authHeader.split("Bearer ");
                String token = authHeaderArr[1];
                return token;
            } catch (Exception e) {
                logger.error("Error get token" + e);
                throw new ResultNotFoundException("token not found " );
            }
        }
        return null;
    }
}
