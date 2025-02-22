package com.icdenge.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

  public static final long JWT_TOKEN_LIFETIME_MS = 8 * 60 * 60 * 1000;

  @Value("${jwt.secret}")
  private String secret;

  private SecretKey secretKey;

  @PostConstruct
  public void init() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    this.secretKey = Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(this.secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload(); // Token'in body kısmını almak için
  }

  private boolean isTokenExpired(String token) {
    return getExpirationDateFromToken(token).before(new Date());
  }

  public String generateToken(Authentication authentication) {
    return Jwts.builder()
        .subject(authentication.getName())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_LIFETIME_MS))
        .signWith(this.secretKey)
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
}
