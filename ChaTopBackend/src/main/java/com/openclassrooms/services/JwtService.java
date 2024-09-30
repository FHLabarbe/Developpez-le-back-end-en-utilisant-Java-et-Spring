package com.openclassrooms.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
  private final String SECRET_KEY = "rentalstokenkey";
  private final long EXPIRATION_TIME = 86400000; // 1 jour

  public String generateToken(String username) {
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public boolean validateToken(String token) {
    return !isTokenExpired(token);
  }

  public String getUsernameFromToken(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody();
    return claims.getSubject();
  }

  private boolean isTokenExpired(String token) {
    final Date expiration = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody()
      .getExpiration();
    return expiration.before(new Date());
  }
}
