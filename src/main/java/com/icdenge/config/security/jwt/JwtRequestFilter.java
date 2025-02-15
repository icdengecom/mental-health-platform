package com.icdenge.config.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
  private final UserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    ContentCachingRequestWrapper cached = new ContentCachingRequestWrapper(request);
    final String requestTokenHeader = cached.getHeader("Authorization");
    String username = null;
    String jwtToken = null;

    if (requestTokenHeader != null) {
      if (requestTokenHeader.startsWith("Bearer ")) {
        jwtToken = requestTokenHeader.substring(7);
        try {
          username = jwtTokenUtil.extractUsername(jwtToken);
        } catch (IllegalArgumentException e) {
          System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
          System.out.println("JWT Token has expired");
        }
      } else {
        logger.warn("JWT Token does not begin with Bearer String");
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(cached));
        SecurityContextHolder.getContext().setAuthentication(token);
      }
    }

    chain.doFilter(cached, response);
  }
}
