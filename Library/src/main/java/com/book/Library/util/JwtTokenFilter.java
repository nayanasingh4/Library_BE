package com.book.Library.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.book.Library.Entity.User;

import io.jsonwebtoken.Claims;



@Component
public class JwtTokenFilter extends OncePerRequestFilter{
    @Autowired
    private JwtTokenUtil jwtUtil;
    
    //Checking authentication using bearear and token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
          if (!hasAuthorizationBearer(request)) {
                filterChain.doFilter(request, response);
                return;
            }
          String token = getAccessToken(request);

            if (!jwtUtil.validateAccessToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            setAuthenticationContext(token, request);
            filterChain.doFilter(request, response);
        // TODO Auto-generated method stub
        
    }
    
    //If authorized request will be processed
      private boolean hasAuthorizationBearer(HttpServletRequest request) {
            String header = request.getHeader("Authorization");
            if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
                return false;
            }

            return true;
        }
      //Getting access Token
      private String getAccessToken(HttpServletRequest request) {
            String header = request.getHeader("Authorization");
            String token = header.split(" ")[1].trim();
            return token;
        }
      
      private void setAuthenticationContext(String token, HttpServletRequest request) {
            UserDetails userDetails = getUserDetails(token);

            UsernamePasswordAuthenticationToken 
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      
      //Getting the details of the user
      private UserDetails getUserDetails(String token) {
    	  User userDetails = new User();
          Claims claims = jwtUtil.parseClaims(token);
          String subject = (String) claims.get(Claims.SUBJECT);
          String[] jwtSubject = subject.split(",");
           userDetails.setUserId(Integer.parseInt(jwtSubject[0]));
          userDetails.setEmailId(jwtSubject[1]);
        return userDetails;
      }



}

