package com.icdenge.config.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;
  private final @Lazy UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      String jwtToken = requestTokenHeader.substring(7);

      try {
        String username = jwtTokenUtil.extractUsername(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);

          if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
        }

      } catch (ExpiredJwtException e) {
        logger.warn("JWT Token expired for user: {}", e);
      } catch (IllegalArgumentException e) {
        logger.warn("Unable to get JWT Token: {}", e);
      }

    } else if (requestTokenHeader != null) {
      logger.warn("Invalid JWT Token format. Must start with 'Bearer '");
    }

    chain.doFilter(request, response);
  }
}

